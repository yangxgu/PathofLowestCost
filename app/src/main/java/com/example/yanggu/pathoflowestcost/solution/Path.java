package com.example.yanggu.pathoflowestcost.solution;

import java.util.List;

public class Path {
    private List<Integer> pathList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Path)) return false;

        Path path = (Path) o;

        return pathList != null ? pathList.equals(path.pathList) : path.pathList == null;

    }

    @Override
    public int hashCode() {
        return pathList != null ? pathList.hashCode() : 0;
    }

    public Path(List<Integer> pathList) {
        this.pathList = pathList;
    }
}
