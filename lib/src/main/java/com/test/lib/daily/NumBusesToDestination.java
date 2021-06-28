package com.test.lib.daily;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class NumBusesToDestination {

    public int numBusesToDestination(int[][] routes, int source, int target) {
        Map<Integer, Set<Integer>> stationsMap = new HashMap<>();//每个站点包含的路线信息
        Map<Integer, Set<Integer>> routesMap = new HashMap<>();//每一条路线包含的站点信息
        Set<Integer> checkRoutes = new HashSet<>();//已经检查过的路线
        Queue<Integer> queue = new LinkedList<>();
        int routeLength = routes.length;
        for (int i = 0; i < routeLength; i++) {
            int[] stations = routes[i];
            routesMap.put(i, new HashSet<>());
            for (int j = 0; j < stations.length; j++) {
                int station = stations[j];
                if (stationsMap.get(station) == null) {
                    stationsMap.put(station, new HashSet<>());
                }
                stationsMap.get(station).add(i);
                routesMap.get(i).add(station);
            }
        }
        Iterator<Integer> routesIterator = stationsMap.get(source).iterator();
        while (routesIterator.hasNext()) { //将通过这个站点的所有路线入队列
            int route = routesIterator.next();
            queue.add(route);
        }
        int num = 0;
        while (!queue.isEmpty()) {
            num++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int targetRoute = queue.poll();
                checkRoutes.add(targetRoute);
                Iterator<Integer> stationIterator = routesMap.get(targetRoute).iterator();
                while (stationIterator.hasNext()) {
                    int station = stationIterator.next();
                    if (station == target) { //当前站点与目标站点相同，返回
                        return num;
                    }
                    Iterator<Integer> iterator = stationsMap.get(station).iterator();
                    while (iterator.hasNext()) {
                        int route = iterator.next();
                        if (!checkRoutes.contains(route) && !queue.contains(route)) { //当前站点通过的路线还没有检查，入队列
                            queue.add(route);
                        }
                    }
                }
            }
        }
        return -1;
    }

    //    示例 1：
//
//    输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
//    输出：2
//    解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
//    示例 2：
//
//    输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
//    输出：-1

//    [[7,12],[4,5,15],[6],[15,19],[9,12,13]] 15 12

    public static void main(String[] args) {
        System.out.println("result:" + new NumBusesToDestination().numBusesToDestination(new int[][]{{1, 2, 8}, {1, 9, 11}, {2, 5, 7}, {5, 6, 7}}, 1, 6));
        System.out.println("result:" + new NumBusesToDestination().numBusesToDestination(new int[][]{{2}, {2, 8}}, 8, 2));
    }

}
