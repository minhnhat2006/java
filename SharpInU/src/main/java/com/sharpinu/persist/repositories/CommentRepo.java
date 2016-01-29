/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.persist.repositories;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.Comment;

/**
 *
 * @author administrator
 */
public interface CommentRepo extends BaseRepo<Comment, Integer>, CommentCustomRepo<Comment, Integer> {

}
