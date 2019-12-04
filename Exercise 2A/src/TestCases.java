
public class TestCases {
	
	public int[] ports;
	
	public TestCases(int[] ports) {
		this.ports = ports;
	}
	
	public String[] getTestCase(Integer n) {
	
	String[] args = new String[0];
		
		if(n == 1) { 				// small example
	
			args = new String[16];
			args[0] = ""+this.ports[0];
			args[2] = ""+this.ports[1];
			args[4] = ""+this.ports[2];
			args[6] = ""+this.ports[3];
			args[8] = ""+this.ports[4];
			args[10] = ""+this.ports[5];
			args[12] = ""+this.ports[6];
			args[14] = ""+this.ports[7];
		
			args[1] = ""+0;
			args[3] = ""+2;
			args[5] = ""+1;
			args[7] = ""+3;
			args[9] = ""+9;
			args[11] = ""+6;
			args[13] = ""+7;
			args[15] = ""+5;
			
		}else if(n == 2) {				// big example
			
			args = new String[24];
			args[0] = ""+this.ports[0];
			args[2] = ""+this.ports[1];
			args[4] = ""+this.ports[2];
			args[6] = ""+this.ports[3];
			args[8] = ""+this.ports[4];
			args[10] = ""+this.ports[5];
			args[12] = ""+this.ports[6];
			args[14] = ""+this.ports[7];
			args[16] = ""+this.ports[8];
			args[18] = ""+this.ports[9];
			args[20] = ""+this.ports[10];
			args[22] = ""+this.ports[11];
			
			args[1] = ""+8;
			args[3] = ""+10;
			args[5] = ""+1;
			args[7] = ""+6;
			args[9] = ""+2;
			args[11] = ""+3;
			args[13] = ""+12;
			args[15] = ""+11;
			args[17] = ""+5;
			args[19] = ""+4;
			args[21] = ""+9;
			args[23] = ""+7;
			
		}else if(n == 3) {				// example 3:    4 components increasing
			
			args = new String[8];
			args[0] = ""+this.ports[0];
			args[2] = ""+this.ports[1];
			args[4] = ""+this.ports[2];
			args[6] = ""+this.ports[3];
			
			args[1] = ""+1;
			args[3] = ""+2;
			args[5] = ""+3;
			args[7] = ""+4;
			
		}else if(n == 4) {				// example 4:    4 components decreasing
			
			args = new String[8];
			args[0] = ""+this.ports[0];
			args[2] = ""+this.ports[1];
			args[4] = ""+this.ports[2];
			args[6] = ""+this.ports[3];
			
			args[1] = ""+4;
			args[3] = ""+3;
			args[5] = ""+2;
			args[7] = ""+1;
			
		}else if(n == 5) {				// example 5:    2 components big small
			
			args = new String[4];
			args[0] = ""+this.ports[0];
			args[2] = ""+this.ports[1];
			
			args[1] = ""+13;
			args[3] = ""+7;	
			
		}else if(n == 6) {				// example 6:    2 components small big
			
			args = new String[4];
			args[0] = ""+this.ports[0];
			args[2] = ""+this.ports[1];
			
			args[1] = ""+7;
			args[3] = ""+13;
			
		}else if(n == 7) {				// example 7:    single component
			
			args = new String[2];
			args[0] = ""+this.ports[0];
			
			args[1] = ""+7;
			
		}else if(n == 8) {				// example 8:    n = 2^k where k is thus 3
			
			args = new String[16];
			args[0] = ""+this.ports[0];
			args[2] = ""+this.ports[1];
			args[4] = ""+this.ports[2];
			args[6] = ""+this.ports[3];
			args[8] = ""+this.ports[4];
			args[10] = ""+this.ports[5];
			args[12] = ""+this.ports[6];
			args[14] = ""+this.ports[7];
			
			args[1] = ""+0;
			args[3] = ""+4;
			args[5] = ""+2;
			args[7] = ""+6;
			args[9] = ""+1;
			args[11] = ""+5;
			args[13] = ""+3;
			args[15] = ""+7;
			
		}
		
		return args;
	}
}
