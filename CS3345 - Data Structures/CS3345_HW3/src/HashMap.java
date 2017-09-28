import java.util.Arrays;

public class HashMap {
	
	public static HashNode[] LinearProbing;
	public static HashNode[] QuadraticProbing;
	public static HashNode[][] SeperateChaining;
	public static HashNode[] DoubleHashing;
	
	public HashMap() {
		LinearProbing = new HashNode[2];
		QuadraticProbing = new HashNode[2];
		SeperateChaining = new HashNode[2][0];
		DoubleHashing = new HashNode[2];
	}
	
	public void insert(String key, String type, String def) {
		//insertLinearProbing(key, type, def);
		insertQuadraticProbing(key, type, def);
		//insertSeperateChaining(key, type, def);
		//insertDoubleHashing(key, type, def);
	}
	
	private void insertLinearProbing(String key, String type, String def) {
		int hash = 1; // Starting HASH at a prime number, to produce more unique results
		int linear_probe = 0;
		
		// Initial Hash
		for(int i = 0; i < key.length(); i++) {
			hash = 3 * hash + key.charAt(i); // 31 is prime, and helps to produce more unique results
		}
		
		hash = Math.abs((hash + linear_probe) % LinearProbing.length);
		
		while(LinearProbing[hash] != null) {					
			hash = Math.abs((hash + linear_probe) % LinearProbing.length);		
			linear_probe++;
		}
		LinearProbing[hash] = new HashNode(key, type, def);		
		
		if(getNumElements(LinearProbing) >= LinearProbing.length) {
			HashNode[] newTable = new HashNode[LinearProbing.length*2];
			for(int i = 0; i < LinearProbing.length; i++) {
				newTable[i] = LinearProbing[i];
			}
			LinearProbing = newTable;
		}
	}
	
	// Need to fix issue w/ Overlapping (Ya know, cause quadratic can go to the same value alot) <- that isnt actually a comment I would keep btw, its like 12:01am, I have a test tmrw 'technically today' and I'm tired so pls dont hate
	private void insertQuadraticProbing(String key, String type, String def) {
		int hash = 1; // Starting HASH at a prime number, to produce more unique results
		int quadratic_probe = 0;
		
		// Initial Hash
		for(int i = 0; i < key.length(); i++) {
			hash = 3 * hash + key.charAt(i); // 31 is prime, and helps to produce more unique results
		}
		
		hash = Math.abs((hash + (int)Math.pow(quadratic_probe, 2)) % QuadraticProbing.length);
		int parentHash = hash;
		while(QuadraticProbing[hash] != null) {					
			hash = Math.abs((hash + (int)Math.pow(quadratic_probe, 2)) % QuadraticProbing.length);		
			quadratic_probe++;				
		}
		
		QuadraticProbing[hash] = new HashNode(key, type, def);		
		
		if(getNumElements(QuadraticProbing) >= QuadraticProbing.length) {
			HashNode[] newTable = new HashNode[QuadraticProbing.length*2];
			for(int i = 0; i < QuadraticProbing.length; i++) {
				newTable[i] = QuadraticProbing[i];
			}
			QuadraticProbing = newTable;
		}
	}
	
	private void insertSeperateChaining(String key, String type, String def) {
		int hash = 1; // Starting HASH at a prime number, to produce more unique results				    
		
		// Initial Hash
		for(int i = 0; i < key.length(); i++) {
			hash = 3 * hash + key.charAt(i); // 31 is prime, and helps to produce more unique results
		}		
		hash = Math.abs(hash % SeperateChaining.length);

		if(SeperateChaining[hash] == null) {
			SeperateChaining[hash] = new HashNode[2];
			SeperateChaining[hash][0] = new HashNode(key, type, def); 
		} else {
			SeperateChaining[hash][getFreeSpace(SeperateChaining[hash])] = new HashNode(key, type, def);
			if(getNumElements(SeperateChaining[hash]) >= SeperateChaining[hash].length) {
				HashNode[] newTable = new HashNode[SeperateChaining[hash].length*2];
				for(int i = 0; i < SeperateChaining[hash].length; i++) {
					newTable[i] = SeperateChaining[hash][i];
				}
				SeperateChaining[hash] = newTable;
			}
		}
	}
	
	private void insertDoubleHashing(String key, String type, String def) {
		int fhash = 1; // Starting HASH at a prime number, to produce more unique results	
		int ghash = 1; // Starting HASH at a prime number, to produce more unique results	
		
		// Initial Hash 
		for(int i = 0; i < key.length(); i++) {
			fhash = 3 * fhash + key.charAt(i); // 31 is prime, and helps to produce more unique results
		}		
		ghash = Math.abs(getClosestPrime(DoubleHashing) - fhash % DoubleHashing.length);
		
		fhash = Math.abs((fhash + ghash) % DoubleHashing.length);		
		while(DoubleHashing[fhash] != null) {
			fhash = Math.abs((fhash + ghash) % DoubleHashing.length);
		}		
		DoubleHashing[fhash] = new HashNode(key, type, def);
		
	}
	
	private int getClosestPrime(HashNode[] array) {
		// Use Sieve of Eratosthenes for primes : *Thanks Project Euler!*		
		Boolean[] sieve = new Boolean[array.length];
		Arrays.fill(sieve, true);
		
		for(int i = 2; i < (int)Math.sqrt(array.length); i++) {
			if(sieve[i]) {
				for(int j = (int)Math.pow(i,2); j < array.length; j += i) {
					sieve[j] = false;
				}
			}
		}
		
		int prime = -1;
		for(int i = sieve.length-1; i > 0; i--) {
			if(sieve[i]) {
				prime = i;
				break;
			}
		}
		
		return prime;
	}

	private int getFreeSpace(HashNode[] array) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == null) {
				return i;
			}
		}		
		return -1; // Should never get to this point
	}

	private int getNumElements(HashNode[] array) {		
		int numElements = 0;
		for(HashNode node : array) {
			if(node == null) // Should never get past array.length/2
				break;
			numElements++;
		}		
		return numElements;
	}

	public HashNode[] getLinearProbing() {
		return LinearProbing;
	}
	
	public HashNode[] getQuadraticProbing() {
		return QuadraticProbing;
	}
	
	public HashNode[][] getSeperateChaining() {
		return SeperateChaining;
	}
	
	public HashNode[] getDoubleHashing() {
		return DoubleHashing;
	}
}
