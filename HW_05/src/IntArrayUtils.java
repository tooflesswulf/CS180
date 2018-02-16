import java.util.StringJoiner;

/**
 * A group of utility methods for {@code int} arrays.
 *
 * <p>CS18000 -- Spring 2018 -- Week Six -- Homework</p>
 */
public final class IntArrayUtils {
    /**
     * Constructs a newly allocated {@code IntArrayUtils} object.
     */
    private IntArrayUtils() {} //IntArrayUtils

    /**
     * Gets the sum of the values in the specified array. If the specified array is {@code null}, zero should be
     * returned.
     *
     * Sample usage:
     *   Input:        null
     *   Return value: 0
     *
     *   Input:        new int[] {-1, 1 -1, 1}
     *   Return value: 0
     *
     *   Input:        new int[] {1, 2, 3, 4, 5}
     *   Return value: 15
     *
     *   Input:        new int[] {37, -40, 10, -88, 27}
     *   Return value: -54
     *
     * @param arr the array to be summed
     * @return the sum of the values in the specified array
     */
    public static int sum(int[] arr) {
        if(arr==null) return 0;
        
        int sum=0;
        for(int i:arr) {
            sum += i;
        }

        return sum;
    } //sum

    /**
     * Gets the product of the values in the specified array. If the specified array is {@code null}, zero should be
     * returned.
     *
     * Sample usage:
     *   Input:        null
     *   Return value: 0
     *
     *   Input:        new int[] {-1, 1 -1, 1}
     *   Return value: 1
     *
     *   Input:        new int[] {1, 2, 3, 4, 5}
     *   Return value: 120
     *
     *   Input:        new int[] {37, -40, 10, -88, 27}
     *   Return value: 35164800
     *
     * @param arr the array to be multiplied
     * @return the product of the values in the specified array
     */
    public static int product(int[] arr) {
        if(arr==null) return 0;
        if(arr.length==0) return 0;
        
        int prod=1;
        for(int i:arr) {
            prod *= i;
        }
    
        return prod;
    } //product

    /**
     * Gets the maximum value in the specified array. If the specified array is {@code null} or empty, zero should be
     * returned.
     *
     * Sample usage:
     *   Input:        null
     *   Return value: 0
     *
     *   Input:        new int[] {}
     *   Return value: 0
     *
     *   Input:        new int[] {-1, 1 -1, 1}
     *   Return value: 1
     *
     *   Input:        new int[] {1, 2, 3, 4, 5}
     *   Return value: 5
     *
     *   Input:        new int[] {37, -40, 10, -88, 27}
     *   Return value: 37
     *
     * @param arr the array to be searched
     * @return the maximum value in the specified array
     */
    public static int maxValue(int[] arr) {
        if(arr==null) return 0;
        if(arr.length==0) return 0;
        
        int max = Integer.MIN_VALUE;
        
        for(int i:arr) {
            if(i > max) max = i;
        }

        return max;
    } //maxValue

    /**
     * Gets the minimum value in the specified array. If the specified array is {@code null} or empty, zero should be
     * returned.
     *
     * Sample usage:
     *   Input:        null
     *   Return value: 0
     *
     *   Input:        new int[] {}
     *   Return value: 0
     *
     *   Input:        new int[] {-1, 1 -1, 1}
     *   Return value: -1
     *
     *   Input:        new int[] {1, 2, 3, 4, 5}
     *   Return value: 1
     *
     *   Input:        new int[] {37, -40, 10, -88, 27}
     *   Return value: -88
     *
     * @param arr the array to be searched
     * @return the minimum value in the specified array
     */
    public static int minValue(int[] arr) {
        if(arr==null) return 0;
        if(arr.length==0) return 0;
        
        int min = Integer.MAX_VALUE;
        for(int i:arr) {
            if(i < min) min = i;
        }

        return min;
    } //minValue

    /**
     * Gets the index of the first occurrence of the specified value in the specified array, or {@code -1} if it could
     * not be found. If the specified array is {@code null}, {@code -1} should be returned.
     *
     * Sample usage:
     *   Input:        null, 5
     *   Return value: -1
     *
     *   Input:        new int[] {}, 74
     *   Return value: -1
     *
     *   Input:        new int[] {-1, 1 -1, 1}, 1
     *   Return value: 1
     *
     *   Input:        new int[] {1, 2, 3, 4, 5}, 4
     *   Return value: 3
     *
     *   Input:        new int[] {37, -40, 10, -88, 27}, 10
     *   Return value: 2
     *
     * @param arr the array to be searched
     * @param value the value to be searched for
     * @return the index of the first occurrence of the specified value in the specified array, or {@code -1} if it
     * could not be found
     */
    public static int indexOf(int[] arr, int value) {
        if(arr==null) return -1;
        
        for(int i=0; i<arr.length; ++i) {
            if(arr[i] == value) return i;
        }

        return -1;
    } //indexOf

    /**
     * Gets the index of the last occurrence of the specified value in the specified array, or {@code -1} if it could
     * not be found. If the specified array is {@code null}, {@code -1} should be returned.
     *
     * Sample usage:
     *   Input:        null, 5
     *   Return value: -1
     *
     *   Input:        new int[] {}, 74
     *   Return value: -1
     *
     *   Input:        new int[] {-1, 1 -1, 1}, 1
     *   Return value: 3
     *
     *   Input:        new int[] {10, 10, 10, 9, 10, 9}, 10
     *   Return value: 4
     *
     *   Input:        new int[] {37, -40, 10, -88, 27}, -40
     *   Return value: 1
     *
     * @param arr the array to be searched
     * @param value the value to be searched for
     * @return the index of the last occurrence of the specified value in the specified array, or {@code -1} if it
     * could not be found
     */
    public static int lastIndexOf(int[] arr, int value) {
        if(arr==null) return -1;
        
        for(int i=arr.length-1; i>=0; --i) {
            if(arr[i] == value) return i;
        }

        return -1;
    } //lastIndexOf

    /**
     * Determines whether or not {@code arr} is equal to {@code anotherArray}. {@code true} is returned if both
     * arrays are {@code null}, or if both arrays are of equal length, and contain the same values.
     *
     * Sample usage:
     *   Input:        null, null
     *   Return value: true
     *
     *   Input:        null, new int[] {}
     *   Return value: false
     *
     *   Input:        new int[] {}, null
     *   Return value: false
     *
     *   Input:        new int[] {}, new int[] {}
     *   Return value: true
     *
     *   Input:        new int[] {0, 2, 4, 6}, new int[] {0, 2, 4}
     *   Return value: false
     *
     *   Input:        new int[] {0, 2, 4, 6}, new int[] {0, 1, 3, 5}
     *   Return value: false
     *
     *   Input:        new int[] {1, 2, 3}, new int[] {1, 2, 3}
     *   Return value: true
     *
     * @param arr the array to be compared to {@code anotherArray}
     * @param arr2 the array to be compared to {@code arr}
     * @return {@code true}, if {@code arr} is equal to {@code anotherArray}, and {@code false} otherwise
     */
    public static boolean equals(int[] arr, int[] arr2) {
        if(arr==null && arr2==null) return true;
        if(arr==null) return false;
        if(arr2==null) return false;

        if(arr.length != arr2.length) return false;

        for(int i=0; i<arr.length; ++i) {
            if(arr[i] != arr2[i]) return false;
        }

        return true;
    } //equals

    /**
     * Gets a {@code String} representation of the specified array. The returned {@code String} is of the form
     * {@code [arr[0], arr[1], arr[2], ... arr[n - 1]]}, where {@code n} is the length of the specified
     * array.
     *
     * Sample usage:
     *   Input:        null
     *   Return value: null
     *
     *   Input:        new int[] {}
     *   Return value: "[]"
     *
     *   Input:        new int[] {0, 2, 4, 6}
     *   Return value: "[0, 2, 4, 6]"
     *
     *   Input:        new int[] {-1, 0, 1}
     *   Return value: "[-1, 0, 1]"
     *
     * @param arr the array to be returned as a {@code String}
     * @return a {@code String} representation of the specified array
     */
    public static String toString(int[] arr) {
        if(arr==null) return null;
        StringJoiner sj = new StringJoiner(", ");
        
        for(int i:arr) {
            sj.add(String.valueOf(i));
        }
        
        return "[" + sj.toString() + "]";
    } //toString
}