package com.biblioteca.carlos.interfacs.services;

import com.biblioteca.carlos.model.Editorial;
import java.util.List;
import java.util.Optional;

public interface IEditorialService {

    public List<Editorial> list();
    public Optional<Editorial> listId(Long id);
    public Editorial save(Editorial p);
    public Editorial update(Long id, Editorial newUser);
    public boolean delete(Long userId);


}