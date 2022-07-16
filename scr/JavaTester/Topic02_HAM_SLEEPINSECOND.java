package JavaTester;

public class Topic02_HAM_SLEEPINSECOND {
	
	public void sleepInsecond (long second) {
		try {
			Thread.sleep(second *1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
