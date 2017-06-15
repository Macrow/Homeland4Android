package org.mstudio.homeland4android.data.network.model.raw;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/6/13
 * desc   :
 */
public class RawLikeResult {

    /**
     * obj_type : topic
     * obj_id : 33201
     * count : 1
     */

    private String obj_type;
    private int obj_id;
    private int count;

    public String getObj_type() {
        return obj_type;
    }

    public void setObj_type(String obj_type) {
        this.obj_type = obj_type;
    }

    public int getObj_id() {
        return obj_id;
    }

    public void setObj_id(int obj_id) {
        this.obj_id = obj_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
