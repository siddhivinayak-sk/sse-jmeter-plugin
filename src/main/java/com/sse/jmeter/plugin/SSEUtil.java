package com.sse.jmeter.plugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.slf4j.Log4jLogger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;

import reactor.core.publisher.Flux;

public class SSEUtil {
	
	public static void main(String...args) {
		System.out.println(System.getProperty("java.version"));
		
    	Map<String, String> headers = new HashMap<String, String>();
    	headers.put("Authorization", "Bearer eyJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwiYWxnIjoiZGlyIn0..Zro-KhxZl-FeWYRsYC_QBA.b7q9Te-kLGF_7L_Y6O2PhBic4C3LWGjlbGhTNhLUSZ2OL3lTaELcv6s3_svqw1EF2v5VPvhhVpq4KpSe2LiCA1kcurtEPU68d-C0edFCV2j4in6MZeJ5hs30MILVcPO2okyiq1P1S60xm7O-BZHM8h0Bi9Qt9aIK_ifYZYTazlt2IfTDy7Kfe0GHTAdIWPc4nMIautPELDbIseONbHeShgjxdhGEThYQ7SWWeXOYDtQHhOPCordXymrHfKIJ09lEqRv3d3JovnpkHVy8lxglEVhX3iVMaGpzQGlFLDODZd1nWXKIypnPHskPiUkTjc5PnF7IFghQbUl_6vjOCIM26djw6Igg9Nu5wqWTePn0j-4BpA-y2SxKjKIjWxT-dC0HhMAIwt-MX0BALvZWJg-CNq66jQacMfvHJmBUgCfUpV7nYqEfs8SD1Qtc4TUBDA1ybXEV0e9tEYuOCeNH_CkuDgCxINWww2Ce-DSiOQXL8zrfSLSYGP8n7SeqneSYuHRA0-sSQBnoO6hc8l98wKxv2U13WAOts-C_Yt3NjTv6i38a_snrDjobZxxSYbqzC9DXUojJEr3nc-CsbVm3fwXkhPo-TPGWXP-exyk4A9jjW2dht4PsdVHi0rs-mObgOZk7yhukvIN5vLlL-GWo2L4zpzprSlzPqzIDIvgUSkKRR9MMmKwBNzowDStQxhWTi6aJG-5rlqbTPKZamxLq_5MbhH63t7EbEhpjue4kYVZOV85XiskishJS3phCyDatA8gyextc7jhlCk0CuRAOui9bmBjq5JpY1oe-fNhkAMguJ8r0-bG2hUn915J_hRG--CEeIZlZSie8c25SFdCabgFUCrGf_H4E52MBQwioYcOAZ8weyxCjjkdcPJI9_kDlvluXW_EoD-C3t05zdlIdn6XyGoNPcJoafXnJ5UAAPhYcTaA767fCriqsfE8w3C-pCQ6Gpp-gtdwtDJt7mB_gfnpQrnGi_zg_SiKRvvOSJAJkFcl7EYoJCwsZeVARoIovDZdqk7xPdD58y77EIwZqwpz6MOv3S0ZkCF76J-BYV5da3CL_ZKdvHNoB7p50BCoIDGkY8K7QWUIEVHoub4e4p2psPBJeklVbP19BPUa3gtAd6eJJPy2d5G1fQJ5ixt_3Ke7qtJfJK0p3vHkGX2WJWYI5ZKevhn-y9ZrB603ZnVoGwP-j3jWKFyBMyEH6P4A0oYy4rRw0_kGwS2JDpfSSy6xB5cf0YpdMgFoEDI0Hm7lwM4OAWXNBz_GatzaJfT_3Y4YcIk9QcMJnrR4SdIU_4iyf7wUTnij-GIuInlcvts8hkJ1m-eD_JIgmWNA8Cy3Ugrj0fhWBPdlxo1jcNrqoCgpFLEmJMmqknSjPdGVv3XI9CLd4V1Ae3bCr12jz9SXaQuUfJHBvBiViLJWFrU79qmSLExWD3UcuQRhloJDpiXb8wfqqd3uSALj9s2Bv_VRhpDTIKH7NuGwSvKwUnU_7LPBSQ2CaavTpLuEVgYhjtDr5aFS6OB4CWzcTAtbG1BNm1tjFm-3vb4OCoYpkxMHT123sRe5pfgAOznSrmQDe0zVr9RizL7gcEWuytsYR1Z60GwKQs2cPeWi4v_IJ8v4EJjq8lOjbQsbuoOHnr8ONoDXykGUEROV93iz4INcu-K96cwYKNMHDUirfD0nVbNfmW5US8dKkC2cgHK--x90-nOhSG1i7ewOwCqYpdZwQ6bOK7yqXIvvjrcJlYYAfAzYIfUVhLQlLnlKZ3_x0-tUcmC4dtuZ9fHx_oZXJ9QVcIYZtNOGrW2L8bmbSmkAqMnwZad3a2iNrvrxgSh6IdBpRQQKfkDJIMF22lV45hti4vTtTRPKr0Rog0r_9p9T8aojzi54n6aK_gTeBP-oC-0lI43auLi2AzSuH-jjxuHF1wXTonizT-wlijW6QuNMyIMh-fRw0e5K4lNUbqMvZ4P1yOZo_y8aHh3IxVXLemIL5lrtfZgIcYFxuxzGoRW4SB6p23Bmxn8cvnYA8pelzLWAdQjKf-eyM2a0yJO8pCwFel6IygZjhLHTu4R9haVEob-251x4ynjud-orXCrxCYCDq03FWCFY6KxSafpyA4Noq3o6M3Zi41TKImlbVxAjej7CNK0lT1Z4qTJLYNsOq4oEMINlkaxkezQssfSYSCHjSGsjG9kQh5f7rYxwPNxlCGYVmUzUqNiHkM4WmsZEz6n3ONR10T3qievQNSrAm90mQE02fQlT-MRobvPT93BB0hfAf4q7t1FYiJx4dAJm-UefgrcZFL4Qrug_hfhFcfknkDVx_xpUH.UJsUTATTeSsG93DpXXSoUw");
    	SSEUtil util = new SSEUtil();

    	boolean result = util.sseTest("https://login.core-2.dev22r1.sbcp.io/service-sample-sse/api/sse?events=3&delay=1000", headers, Arrays.asList("1", "2", "3"), 150, 1000, null);
    	//boolean result = util.sseTest("http://localhost:8080/api/sse?events=3&delay=1000", headers, Arrays.asList("1", "2", "3"), 60, 1000, null);
    	//boolean result = util.sseTest("http://localhost:8080/api/sse/custom?events=3&delay=1000", headers, Arrays.asList("1", "2", "3"), 60, 1000, null);
    	//boolean result = util.sseTest("http://localhost:8080/api/sse/simple?events=3&delay=1000", headers, Arrays.asList("1", "2", "3"), 60, 1000, null);

    	System.out.println(result);
	}
	
	private WebClient webClient;
	
	public boolean sseTest(
			String url, 
			Map<String, String> headers,
			List<String> expect,
			int tf,
			int delay,
			Log4jLogger logger
			) {
		try {
			
			if(null != logger) {
				logger.info("URL: " + url);
				logger.info("Headers: " + headers);
				logger.info("Expect: " + expect);
				logger.info("TF: " + tf);
				logger.info("Delay: " + delay);
			}
			
			Timekeeper<String> timekeeper = new Timekeeper<>();
			webClient = WebClient.builder()
					.baseUrl(url)
					.build();
	
			RequestHeadersUriSpec<?> requestHeadersUriSpec = webClient.get();
			headers.entrySet().forEach(entry -> {
				requestHeadersUriSpec.header(entry.getKey(), entry.getValue());
			});
			
			requestHeadersUriSpec.exchange().subscribe((cr) -> {
				System.out.println(cr.statusCode());
			});
			
			WebClient.ResponseSpec responseSpec = requestHeadersUriSpec.retrieve();
			
			ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<String>() {};
			Flux<String> sseResponse = responseSpec.bodyToFlux(typeRef);
			Flux<String> sseIds = sseResponse.map(sse -> sse);

			AtomicInteger ai = new AtomicInteger();
			sseIds.subscribe((id) -> {
				ai.incrementAndGet();
				timekeeper.getElements().put(ai.get(), id);
				timekeeper.getSeries().put(ai.get(), System.currentTimeMillis());
				if(null != logger) {
					logger.info("Data " + ai.get() + ": " + id);
				}
			}, (err) -> {
				timekeeper.setTh(err);
				timekeeper.setError(true);
				if(null != logger) {
					logger.error("ErrorAtFetch: ", err);			
				}
			}, () -> {
				timekeeper.setEndTime(System.currentTimeMillis());
			});
			timekeeper.setStartTime(System.currentTimeMillis());
			
			Thread.sleep(2 * delay * expect.size());
			
			if(timekeeper.series.size() != expect.size()) {
				throw new TestException("Assertion failed: Excepted size (" + expect.size() + ") vs found size (" + timekeeper.series.size() + ")");
			}
			for(int counter = 0; counter < expect.size(); counter++) {
				if(!timekeeper.elements.get(counter + 1).contains(expect.get(counter))) {
					throw new TestException("Assertion failed: Excepted element vs found element mismatch: " + expect.get(counter) + " != " + timekeeper.elements.get(counter + 1));
				}
				if(!intersect(tf, (counter + 1) * delay, timekeeper.consumedTimeForXth(counter + 1))) {
					throw new TestException("Assertion failed: Time is not as expected for " + (counter + 1) + "th item: " + timekeeper.consumedTimeForXth(counter + 1));
				} 
			}
			if(!intersect(tf, expect.size() * delay, timekeeper.totalConsumedTime())) {
				throw new TestException("Assertion failed: Total time is not as expected: " + timekeeper.totalConsumedTime());
			}
			return !timekeeper.isError();
		}
		catch(Exception | TestException ex) {
			if(null != logger) {
				logger.error("Error: ", ex);			
			}
			ex.printStackTrace();
			return false;
		}
	}

	private boolean intersect(int tf, long baseMills, long obtainedMills) {
		long minMills = baseMills - ((baseMills * tf) / 100);
		long maxMills = baseMills + ((baseMills * tf) / 100);
		return obtainedMills >= minMills && obtainedMills <= maxMills; 
	}
	
	static class Timekeeper<T> {
		private Long startTime;
		private Long endTime;
		private boolean error;
		private Throwable th;
		private Map<Integer, T> elements = new HashMap<>();
		private Map<Integer, Long> series = new HashMap<>();
		public Long getStartTime() {
			return startTime;
		}
		public void setStartTime(Long startTime) {
			this.startTime = startTime;
		}
		public Long getEndTime() {
			return endTime;
		}
		public void setEndTime(Long endTime) {
			this.endTime = endTime;
		}
		public boolean isError() {
			return error;
		}
		public void setError(boolean error) {
			this.error = error;
		}
		public Throwable getTh() {
			return th;
		}
		public void setTh(Throwable th) {
			this.th = th;
		}
		public Map<Integer, T> getElements() {
			return elements;
		}
		public void setElements(Map<Integer, T> elements) {
			this.elements = elements;
		}
		public Map<Integer, Long> getSeries() {
			return series;
		}
		public void setSeries(Map<Integer, Long> series) {
			this.series = series;
		}
		
		public long totalConsumedTime() {
			return endTime - startTime;
		}
		
		public long consumedTimeForXth(int xth) {
			return series.get(xth) - startTime;
		}
	}
	
	static class TestException extends Throwable {
		private static final long serialVersionUID = 1L;

		public TestException() {
			super();
		}
		
		public TestException(String message) {
			super(message);
		}
	}
}
