package main.service.interfaces;

import org.springframework.stereotype.Service;

/**
 * Created by ids on 15-3-2017.
 */

public interface IUserService {
    boolean authenticate(String userName, String password);
}
