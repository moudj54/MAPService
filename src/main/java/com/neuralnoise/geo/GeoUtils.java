package com.neuralnoise.geo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderGeometry;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.LatLng;
import com.neuralnoise.map.model.geo.GeographicCoordinates;

import fr.dudie.nominatim.client.JsonNominatimClient;
import fr.dudie.nominatim.model.Address;

public class GeoUtils {

	private static final String PROPS_PATH = "/nominatim-client.properties";

	private GeoUtils() {
	}

	public static GeographicCoordinates queryNominatim(String address, String language) throws IOException {
		HttpClient httpClient = new DefaultHttpClient();

		Properties props = new Properties();
		final InputStream in = GeoUtils.class.getResourceAsStream(PROPS_PATH);
		props.load(in);

		String baseUrl = props.getProperty("nominatim.server.url");
		String email = props.getProperty("nominatim.headerEmail");

		JsonNominatimClient nominatimClient = new JsonNominatimClient(httpClient, email);
		List<Address> addresses = nominatimClient.search(address);

		GeographicCoordinates gc = null;
		Iterator<Address> it = addresses.iterator();

		if (it.hasNext()) {
			Address addr = it.next();
			String formatted = addr.getDisplayName();
			
			long llat = addr.getLatitudeE6();
			long llong = addr.getLongitudeE6();

			gc = new GeographicCoordinates();
			gc.setLatitude(llat);
			gc.setLongitude(llong);
		}

		return gc;
	}

	public static GeographicCoordinates queryGoogle(String address, String language) {
		Geocoder geocoder = new Geocoder();
		GeocoderRequestBuilder builder = new GeocoderRequestBuilder().setAddress(address).setLanguage(language);
		
		GeocoderRequest request = builder.getGeocoderRequest();
		GeocodeResponse response = geocoder.geocode(request);

		GeographicCoordinates gc = null;

		Iterator<GeocoderResult> it = response.getResults().iterator();
		if (it.hasNext()) {
			GeocoderResult result = it.next();
			GeocoderGeometry geometry = result.getGeometry();
			LatLng latlng = geometry.getLocation();
			String formatted = result.getFormattedAddress();

			gc = new GeographicCoordinates();
			gc.setLatitude(latlng.getLat().longValue());
			gc.setLongitude(latlng.getLng().longValue());
		}
		return gc;
	}

}
