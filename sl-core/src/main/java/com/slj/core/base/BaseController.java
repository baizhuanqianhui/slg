package com.slj.core.base;

import com.slj.core.exception.MarkException;

/**
 * @author tingis13
 * @date 2016-10-17
 * @version 1.0
 */
public interface BaseController {
    public abstract void handle(Model model) throws MarkException;
}
