package MonotonicStack;

import java.util.Stack;

public class trappingRainWater {
    public int trap(int[] height) {
        int n = height.length;

        if(n < 2) return 0;

        int totalWater = 0;
        Stack<Integer> stack = new Stack();

        for(int index = 0; index < n; index++){

            while(!stack.empty() && height[index] > height[stack.peek()]){
                //remove and store the previous index
                int prevIndex = stack.pop();

                if(stack.empty()) break;

                int prevToPrevIndex = stack.peek();
                int distance   = index - prevToPrevIndex - 1;

                int boundedHeight = Math.min(height[index], height[prevToPrevIndex]) - height[prevIndex];

                totalWater += distance * boundedHeight;
            }

            stack.push(index);
        }

        return totalWater;
    }
}
