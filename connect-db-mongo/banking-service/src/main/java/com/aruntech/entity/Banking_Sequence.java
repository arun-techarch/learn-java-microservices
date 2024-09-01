package com.aruntech.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Banking_Sequence")
public class Banking_Sequence {
    @Id
    private String id;
    private long seq;

    public Banking_Sequence() {
    }

    public Banking_Sequence(String id, int seq) {
        this.id = id;
        this.seq = seq;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return "Banking_Sequence{" +
                "id='" + id + '\'' +
                ", seq=" + seq +
                '}';
    }
}
