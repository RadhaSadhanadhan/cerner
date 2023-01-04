package com.wipro.swthealthcare;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class SWTHttpRestClientTest {

	@Mock
	HttpRequest httpRequest;

	@Mock
	URI uri;

	@Mock
	HttpClient httpClient;
	@Mock
	HttpResponse httpResponse;
	@Before
	public void doBefore() {
		httpRequest = Mockito.mock(HttpRequest.class);
		uri = Mockito.mock(URI.class);
		httpClient = Mockito.mock(HttpClient.class);
	}
	@Test
	public void testFecthAll() {
		try {
			//Mockito.when(httpRequest.newBuilder().
			//when(httpClient.send(any(), any(HttpResponse.BodyHandlers.ofInputStream().getClass())))
			// .thenReturn(httpResponse);
			SWTHttpRestClient.fecthAllPatients();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
