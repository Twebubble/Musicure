package io.github.Twebubble.musicure.model.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Banner implements Serializable {
    private Integer id;

    private String pic;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if(this == that){
            return true;
        }
        if(that == null){
            return false;
        }
        if(getClass() != that.getClass()){
            return false;
        }
        Banner other = (Banner) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getPic() == null ? other.getPic() == null : getPic().equals(other.getPic()));
    }

    //保证相等的对象有相同的哈希值
    @Override
    public int hashCode() {
        final int prime = 31; //使用质数 31 优化哈希分布
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPic() == null) ? 0 : getPic().hashCode());
        return result;
    }

    //定制输出格式
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pic=").append(pic);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}