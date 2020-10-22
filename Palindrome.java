class Palindrome {

	public static void main (String[] args) {
		String str = "日本語学習を向上させるためのアドバイスをいただけますか";

		System.out.println(isPalindrome(str));
		System.out.println(reverseString(str));
	}

	public static boolean isPalindrome(String str) {
		char[] arr = str.toCharArray(); 
		for(int i = 0; i <= (arr.length - 1)/2; i++) {
			int j = (arr.length - 1) - i;
			if(arr[i] != arr[j]) {
				return false;
			} 
		}
		return true;
	}

	public static String reverseString(String str) {
		char[] arr = str.toCharArray(); 
		for(int i = 0; i <= (arr.length - 1)/2; i++) {
			int j = (arr.length - 1) - i;

			char temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
		return new String(arr);
	}
}
