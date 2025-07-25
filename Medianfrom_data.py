import heapq

class MedianFinder:

    def __init__(self):
        # Max-heap for the left half of the data
        self.max_heap = []
        # Min-heap for the right half of the data
        self.min_heap = []

    def addNum(self, num: int) -> None:
        # Add to max-heap (invert sign for max-heap behavior)
        heapq.heappush(self.max_heap, -num)
        
        # Balance the heaps: Ensure max-heap's max <= min-heap's min
        if self.max_heap and self.min_heap and (-self.max_heap[0] > self.min_heap[0]):
            heapq.heappush(self.min_heap, -heapq.heappop(self.max_heap))
        
        # Balance sizes: max-heap can only have at most 1 more element than min-heap
        if len(self.max_heap) > len(self.min_heap) + 1:
            heapq.heappush(self.min_heap, -heapq.heappop(self.max_heap))
        elif len(self.min_heap) > len(self.max_heap):
            heapq.heappush(self.max_heap, -heapq.heappop(self.min_heap))

    def findMedian(self) -> float:
        # If odd number of elements, median is the root of max-heap
        if len(self.max_heap) > len(self.min_heap):
            return -self.max_heap[0]
        # If even number of elements, median is the average of roots
        return (-self.max_heap[0] + self.min_heap[0]) / 2
