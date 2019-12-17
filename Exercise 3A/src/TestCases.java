
public class TestCases {
	
	public int[] ports;
	
	public TestCases(int[] ports) {
		this.ports = ports;
	}
	
	public String[] getTestCase(Integer n) {
	
	String[] args = new String[0];
		
		if(n == 1) { 				// n = 11, f = 2,  9 processes have value 1 and 2 processes have value 0
	
			args = new String[23];
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
		
			args[1] = ""+1;
			args[3] = ""+1;
			args[5] = ""+1;
			args[7] = ""+1;
			args[9] = ""+1;
			args[11] = ""+1;
			args[13] = ""+1;
			args[15] = ""+1;
			args[17] = ""+1;
			args[19] = ""+0;
			args[21] = ""+0;
			
			args[22] = ""+2;
			
		}else if(n == 2) {				// n = 11, f = 2, 5 processes have value 1 and 6 processes value 0
			
			args = new String[23];
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
		
			args[1] = ""+1;
			args[3] = ""+1;
			args[5] = ""+1;
			args[7] = ""+1;
			args[9] = ""+1;
			args[11] = ""+0;
			args[13] = ""+0;
			args[15] = ""+0;
			args[17] = ""+0;
			args[19] = ""+0;
			args[21] = ""+0;
			
			args[22] = ""+2;
			
		}else if(n == 3) {				// n = 11, f = 3, 5 processes have value 1 and 6 processes value 0
			
			args = new String[23];
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
		
			args[1] = ""+1;
			args[3] = ""+1;
			args[5] = ""+1;
			args[7] = ""+1;
			args[9] = ""+1;
			args[11] = ""+0;
			args[13] = ""+0;
			args[15] = ""+0;
			args[17] = ""+0;
			args[19] = ""+0;
			args[21] = ""+0;
			
			args[22] = ""+3;
			
		}else if(n == 4) {				// n = 5, f = 0, 2 processes have value 0 an 3 processes have value 1
			
			args = new String[11];
			args[0] = ""+this.ports[0];
			args[2] = ""+this.ports[1];
			args[4] = ""+this.ports[2];
			args[6] = ""+this.ports[3];
			args[8] = ""+this.ports[4];
		
			args[1] = ""+1;
			args[3] = ""+1;
			args[5] = ""+1;
			args[7] = ""+0;
			args[9] = ""+0;
			
			args[10] = ""+0;
			
		}else if(n == 5) {				// n = 5, f = 1, 3 processes have value 1 and 2 processes have with value 0
			
			args = new String[11];
			args[0] = ""+this.ports[0];
			args[2] = ""+this.ports[1];
			args[4] = ""+this.ports[2];
			args[6] = ""+this.ports[3];
			args[8] = ""+this.ports[4];
		
			args[1] = ""+1;
			args[3] = ""+1;
			args[5] = ""+1;
			args[7] = ""+0;
			args[9] = ""+0;
			
			args[10] = ""+1;
			
		}else if(n == 6) {				// n = 5, f = 2, 3 processes value 1 and 2 process with value 0
		
			args = new String[11];
			args[0] = ""+this.ports[0];
			args[2] = ""+this.ports[1];
			args[4] = ""+this.ports[2];
			args[6] = ""+this.ports[3];
			args[8] = ""+this.ports[4];
		
			args[1] = ""+1;
			args[3] = ""+1;
			args[5] = ""+1;
			args[7] = ""+0;
			args[9] = ""+0;
			
			args[10] = ""+2;
			
			
		}else if(n == 7) {				// n = 25, f = 4, 13 processes value 1 and 12 process with value 0
			
			args = new String[51];
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
			args[24] = ""+this.ports[12];
			args[26] = ""+this.ports[13];
			args[28] = ""+this.ports[14];
			args[30] = ""+this.ports[15];
			args[32] = ""+this.ports[16];
			args[34] = ""+this.ports[17];
			args[36] = ""+this.ports[18];
			args[38] = ""+this.ports[19];
			args[40] = ""+this.ports[20];
			args[42] = ""+this.ports[21];
			args[44] = ""+this.ports[22];
			args[46] = ""+this.ports[23];
			args[48] = ""+this.ports[24];
			
			args[1] = ""+1;
			args[3] = ""+1;
			args[5] = ""+1;
			args[7] = ""+1;
			args[9] = ""+1;
			args[11] = ""+1;
			args[13] = ""+1;
			args[15] = ""+1;
			args[17] = ""+1;
			args[19] = ""+1;
			args[21] = ""+1;
			args[23] = ""+1;
			args[25] = ""+1;
			args[27] = ""+0;
			args[29] = ""+0;
			args[31] = ""+0;
			args[33] = ""+0;
			args[35] = ""+0;
			args[37] = ""+0;
			args[39] = ""+0;
			args[41] = ""+0;
			args[43] = ""+0;
			args[45] = ""+0;
			args[47] = ""+0;
			args[49] = ""+0;
			
			args[50] = ""+4;
			
		}else if(n == 8) {				// n = 25, f = 5, 13 processes value 1 and 12 process with value 0    
			
			args = new String[51];
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
			args[24] = ""+this.ports[12];
			args[26] = ""+this.ports[13];
			args[28] = ""+this.ports[14];
			args[30] = ""+this.ports[15];
			args[32] = ""+this.ports[16];
			args[34] = ""+this.ports[17];
			args[36] = ""+this.ports[18];
			args[38] = ""+this.ports[19];
			args[40] = ""+this.ports[20];
			args[42] = ""+this.ports[21];
			args[44] = ""+this.ports[22];
			args[46] = ""+this.ports[23];
			args[48] = ""+this.ports[24];
			
			args[1] = ""+1;
			args[3] = ""+1;
			args[5] = ""+1;
			args[7] = ""+1;
			args[9] = ""+1;
			args[11] = ""+1;
			args[13] = ""+1;
			args[15] = ""+1;
			args[17] = ""+1;
			args[19] = ""+1;
			args[21] = ""+1;
			args[23] = ""+1;
			args[25] = ""+1;
			args[27] = ""+0;
			args[29] = ""+0;
			args[31] = ""+0;
			args[33] = ""+0;
			args[35] = ""+0;
			args[37] = ""+0;
			args[39] = ""+0;
			args[41] = ""+0;
			args[43] = ""+0;
			args[45] = ""+0;
			args[47] = ""+0;
			args[49] = ""+0;
			
			args[50] = ""+5;
			
		}
		
		return args;
	}
}
