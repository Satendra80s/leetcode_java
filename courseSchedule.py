

from collections import defaultdict, deque
from typing import List

class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
      
        # Build adjacency list
        graph = defaultdict(list)
        for course, prereq in prerequisites:
            graph[prereq].append(course)
        
        # 0: white, 1: gray, 2: black
        colors = [0] * numCourses
        
        def has_cycle(node):
            if colors[node] == 1:  # Gray - cycle detected
                return True
            if colors[node] == 2:  # Black - already processed
                return False
            
            colors[node] = 1  # Mark as gray
            for neighbor in graph[node]:
                if has_cycle(neighbor):
                    return True
            colors[node] = 2  # Mark as black
            return False
        
        # Check each node for cycles
        for i in range(numCourses):
            if colors[i] == 0 and has_cycle(i):
                return False
        return True
    
    def canFinish_bfs(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        """
        Approach 2: BFS with Indegree (Kahn's Algorithm)
        Time: O(V + E), Space: O(V + E)
        """
        # Build graph and calculate indegrees
        graph = defaultdict(list)
        indegree = [0] * numCourses
        
        for course, prereq in prerequisites:
            graph[prereq].append(course)
            indegree[course] += 1
        
        # Start with courses having no prerequisites
        queue = deque([i for i in range(numCourses) if indegree[i] == 0])
        completed = 0
        
        while queue:
            course = queue.popleft()
            completed += 1
            
            # Remove this course and update indegrees
            for next_course in graph[course]:
                indegree[next_course] -= 1
                if indegree[next_course] == 0:
                    queue.append(next_course)
        
        return completed == numCourses
    
    def canFinish_simple_dfs(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
       
        graph = defaultdict(list)
        for course, prereq in prerequisites:
            graph[course].append(prereq)
        
        visiting = set()  # Currently in recursion stack
        visited = set()   # Completely processed
        
        def has_cycle(course):
            if course in visiting:
                return True
            if course in visited:
                return False
            
            visiting.add(course)
            for prereq in graph[course]:
                if has_cycle(prereq):
                    return True
            
            visiting.remove(course)
            visited.add(course)
            return False
        
        for i in range(numCourses):
            if has_cycle(i):
                return False
        return True

# Test cases
def test_solutions():
    sol = Solution()
    
    # Test case 1: Can finish - no cycle
    numCourses1 = 2
    prerequisites1 = [[1, 0]]
    print(f"Test 1 - DFS: {sol.canFinish(numCourses1, prerequisites1)}")  # True
    print(f"Test 1 - BFS: {sol.canFinish_bfs(numCourses1, prerequisites1)}")  # True
    
    # Test case 2: Cannot finish - has cycle  
    numCourses2 = 2
    prerequisites2 = [[1, 0], [0, 1]]
    print(f"Test 2 - DFS: {sol.canFinish(numCourses2, prerequisites2)}")  # False
    print(f"Test 2 - BFS: {sol.canFinish_bfs(numCourses2, prerequisites2)}")  # False
    
    # Test case 3: More complex - can finish
    numCourses3 = 4
    prerequisites3 = [[1, 0], [2, 0], [3, 1], [3, 2]]
    print(f"Test 3 - DFS: {sol.canFinish(numCourses3, prerequisites3)}")  # True
    print(f"Test 3 - BFS: {sol.canFinish_bfs(numCourses3, prerequisites3)}")  # True

if __name__ == "__main__":
    test_solutions()

