package org.chitsa.projectmanagement.Service;

import org.chitsa.projectmanagement.Model.User;
import org.chitsa.projectmanagement.Repo.IUserRepo;


public class UserService extends AbstractService<IUserRepo, User> {

        public UserService(IUserRepo userRepo) {
                super(userRepo);
        }
}
