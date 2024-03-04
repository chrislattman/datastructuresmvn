package com.example.dsa;

/**
 * Functions to search and sort lists and arrays.
 */
public class Algorithms {
    /**
     * Default constructor which should not be documented.
     */
    private Algorithms() {
        // Empty constructor
    }

    /**
     * Performs a binary search on a list for a specified key, and returns the
     * index of the first match found. Result is undefined for an unsorted list.
     *
     * @param list list to search in
     * @param key key to find
     * @return index of key in list, or -1 if key not found
     * @param <T> data type which much implement Comparable
     */
    public static <T extends Comparable<? super T>> int binarySearch(
        MyList<T> list, T key) {
        return binarySearch(list, 0, list.size() - 1, key);
    }

    /**
     * Performs a binary search on a range of a list for a specified key, and
     * returns the index of the first match found. Result is undefined for an
     * unsorted list.
     *
     * @param list list to search in
     * @param startIndex index of first element to search
     * @param endIndex index of last element to search
     * @param key key to find
     * @return index of key in array, or -1 if key not found
     * @param <T> data type which much implement Comparable
     */
    public static <T extends Comparable<? super T>> int binarySearch(
        MyList<T> list, int startIndex, int endIndex, T key) {
        while (startIndex <= endIndex) {
            int mid = (startIndex + endIndex) / 2;
            T current = list.get(mid);
            if (current.equals(key)) {
                return mid;
            } else if (current.compareTo(key) > 0) {
                endIndex = mid - 1;
            } else {
                startIndex = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Performs a binary search on an array for a specified key, and returns the
     * index of the first match found. Result is undefined for an unsorted
     * array.
     *
     * @param array array to search in
     * @param key key to find
     * @return index of key in array, or -1 if key not found
     * @param <T> data type which much implement Comparable
     */
    public static <T extends Comparable<? super T>> int binarySearch(
        T[] array, T key) {
        return binarySearch(array, 0, array.length - 1, key);
    }

    /**
     * Performs a binary search on a range of an array for a specified key, and
     * returns the index of the first match found. Result is undefined for an
     * unsorted array.
     *
     * @param array array to search in
     * @param startIndex index of first element to search
     * @param endIndex index of last element to search
     * @param key key to find
     * @return index of key in array, or -1 if key not found
     * @param <T> data type which much implement Comparable
     * @throws IllegalArgumentException if array is not sorted
     */
    public static <T extends Comparable<? super T>> int binarySearch(
        T[] array, int startIndex, int endIndex, T key) {
        while (startIndex <= endIndex) {
            int mid = (startIndex + endIndex) / 2;
            T current = array[mid];
            if (current.equals(key)) {
                return mid;
            } else if (current.compareTo(key) > 0) {
                endIndex = mid - 1;
            } else {
                startIndex = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Performs an in-memory merge sort of the list.
     *
     * @param list list to sort
     * @param <T> data type which much implement Comparable
     */
    public static <T extends Comparable<? super T>> void mergesort(
        MyList<T> list) {
        int size = list.size();
        MyArrayList<T> temp = new MyArrayList<>(size);
        for (int i = 0; i < size; i++) {
            temp.add(list.get(i));
        }
        mergesort(list, temp, 0, size - 1);
    }

    /**
     * Performs an in-memory merge sort of the array.
     *
     * @param array array to sort
     * @param <T> data type which much implement Comparable
     */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void mergesort(T[] array) {
        T[] temp = (T[]) new Comparable[array.length];
        System.arraycopy(array, 0, temp, 0, array.length);
        mergesort(array, temp, 0, array.length - 1);
    }

    /**
     * Performs an in-memory quicksort of the list.
     *
     * @param list list to sort
     * @param <T> data type which much implement Comparable
     */
    public static <T extends Comparable<? super T>> void quicksort(
        MyList<T> list) {
        quicksort(list, 0, list.size() - 1);
    }

    /**
     * Performs an in-memory quicksort of the array.
     *
     * @param array array to sort
     * @param <T> data type which much implement Comparable
     */
    public static <T extends Comparable<? super T>> void quicksort(T[] array) {
        quicksort(array, 0, array.length - 1);
    }

    /**
     * Performs an in-memory heapsort of the list.
     *
     * @param list list to sort
     * @param <T> data type which much implement Comparable
     */
    public static <T extends Comparable<? super T>> void heapsort(
        MyList<T> list) {
        int size = list.size();
        MyPriorityQueue<T> minHeap = new MyPriorityQueue<>(size);
        for (int i = 0; i < size; i++) {
            minHeap.offer(list.remove(0));
        }
        for (int i = 0; i < size; i++) {
            list.add(minHeap.poll());
        }
    }

    /**
     * Performs an in-memory heapsort of the array.
     *
     * @param array array to sort
     * @param <T> data type which much implement Comparable
     */
    public static <T extends Comparable<? super T>> void heapsort(T[] array) {
        MyPriorityQueue<T> minHeap = new MyPriorityQueue<>(array.length);
        for (T element : array) {
            minHeap.offer(element);
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = minHeap.poll();
        }
    }

    /**
     * Internal merge sort divide and conquer function for lists.
     *
     * @param list list to sort
     * @param temp temporary list
     * @param left left index
     * @param right right index
     * @param <T> data type which much implement Comparable
     */
    private static <T extends Comparable<? super T>> void mergesort(
        MyList<T> list, MyList<T> temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergesort(list, temp, left, mid);
            mergesort(list, temp, mid + 1, right);

            int leftPointer = left;
            int rightPointer = mid + 1;
            int counter = left;

            while (leftPointer <= mid && rightPointer <= right) {
                if (list.get(leftPointer).compareTo(
                    list.get(rightPointer)) <= 0) {
                    temp.set(counter++, list.get(leftPointer++));
                } else {
                    temp.set(counter++, list.get(rightPointer++));
                }
            }

            if (leftPointer > mid) {
                while (rightPointer <= right) {
                    temp.set(counter++, list.get(rightPointer++));
                }
            } else {
                while (leftPointer <= mid) {
                    temp.set(counter++, list.get(leftPointer++));
                }
            }

            for (int index = left; index <= right; index++) {
                list.set(index, temp.get(index));
            }
        }
    }

    /**
     * Internal merge sort function for arrays.
     *
     * @param array array to sort
     * @param temp temporary array
     * @param left left index
     * @param right right index
     * @param <T> data type which much implement Comparable
     */
    private static <T extends Comparable<? super T>> void mergesort(
        T[] array, T[] temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergesort(array, temp, left, mid);
            mergesort(array, temp, mid + 1, right);

            int leftPointer = left;
            int rightPointer = mid + 1;
            int counter = left;

            while (leftPointer <= mid && rightPointer <= right) {
                if (array[leftPointer].compareTo(array[rightPointer]) <= 0) {
                    temp[counter++] = array[leftPointer++];
                } else {
                    temp[counter++] = array[rightPointer++];
                }
            }

            if (leftPointer > mid) {
                while (rightPointer <= right) {
                    temp[counter++] = array[rightPointer++];
                }
            } else {
                while (leftPointer <= mid) {
                    temp[counter++] = array[leftPointer++];
                }
            }
            System.arraycopy(temp, left, array, left, right - left + 1);
        }
    }

    /**
     * Internal high level quicksort function for lists.
     *
     * @param list list to sort
     * @param left left index
     * @param right right index
     * @param <T> data type which much implement Comparable
     */
    private static <T extends Comparable<? super T>> void quicksort(
        MyList<T> list, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(list, left, right);
            quicksort(list, left, pivotIndex - 1);
            quicksort(list, pivotIndex, right);
        }
    }

    /**
     * Partition function for quicksort for lists.
     *
     * @param list list to sort
     * @param left left index
     * @param right right index
     * @return pivot location
     * @param <T> data type which much implement Comparable
     */
    private static <T extends Comparable<? super T>> int partition(
        MyList<T> list, int left, int right) {
        T pivot;
        if (right - left > 1) {
            pivot = findMedian(
                list.get(left),
                list.get((left + right) / 2),
                list.get(right)
            );
        } else {
            pivot = list.get(left);
        }

        while (left <= right) {
            while (list.get(left).compareTo(pivot) < 0) {
                left++;
            }
            while (list.get(right).compareTo(pivot) > 0) {
                right--;
            }
            if (left <= right) {
                T temp = list.get(left);
                list.set(left, list.get(right));
                list.set(right, temp);
                left++;
                right--;
            }
        }
        return left;
    }

    /**
     * Internal high level quicksort function for arrays.
     *
     * @param array array to sort
     * @param left left index
     * @param right right index
     * @param <T> data type which much implement Comparable
     */
    private static <T extends Comparable<? super T>> void quicksort(
        T[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(array, left, right);
            quicksort(array, left, pivotIndex - 1);
            quicksort(array, pivotIndex, right);
        }
    }

    /**
     * Partition function for quicksort for arrays.
     *
     * @param array array to sort
     * @param left left index
     * @param right right index
     * @return pivot location
     * @param <T> data type which much implement Comparable
     */
    private static <T extends Comparable<? super T>> int partition(
        T[] array, int left, int right) {
        T pivot;
        if (right - left > 1) {
            pivot = findMedian(
                array[left],
                array[(left + right) / 2],
                array[right]
            );
        } else {
            pivot = array[left];
        }

        while (left <= right) {
            while (array[left].compareTo(pivot) < 0) {
                left++;
            }
            while (array[right].compareTo(pivot) > 0) {
                right--;
            }
            if (left <= right) {
                T temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }
        return left;
    }

    /**
     * Finds the median of three values. Used for quicksort.
     *
     * @param a first value
     * @param b second value
     * @param c third value
     * @return the median of the first, second, and third values
     * @param <T> data type which much implement Comparable
     */
    private static <T extends Comparable<? super T>> T findMedian(
        T a, T b, T c) {
        boolean aLEb = a.compareTo(b) <= 0;
        boolean bLEc = b.compareTo(c) <= 0;
        boolean aLEc = a.compareTo(c) <= 0;
        boolean cLEb = c.compareTo(b) <= 0;
        boolean bLEa = b.compareTo(a) <= 0;
        boolean cLEa = c.compareTo(a) <= 0;

        if (bLEa && aLEc || cLEa && aLEb) {
            return a;
        } else if (aLEb && bLEc || cLEb && bLEa) {
            return b;
        } else {
            return c;
        }
    }
}
