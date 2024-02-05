package org.example.comm.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Likes {
    List<Like> likes;

    public static Likes buildLikes(String... likes) {
        Likes likes1 = new Likes();
        List<Like> list = new ArrayList<>();
        for (int i = 0; i < likes.length; i += 2) {
            Like like = new Like();
            like.setName(likes[i]);
            like.setDescription(likes[i + 1]);
            list.add(like);
        }
        likes1.setLikes(list);
        return likes1;
    }
}
