package com.Pojo.DTO;

public class MaximumQuantityDto{
    public int courseId;
    public int maximumQuantity;

    public MaximumQuantityDto() {
    }

    public MaximumQuantityDto(int courseId, int maximumQuantity) {
        this.courseId = courseId;
        this.maximumQuantity = maximumQuantity;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getMaximumQuantity() {
        return maximumQuantity;
    }

    public void setMaximumQuantity(int maximumQuantity) {
        this.maximumQuantity = maximumQuantity;
    }
}
