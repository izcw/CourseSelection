package com.Service;

import com.IService.IBaseService;
import com.Repository.BaseRepository;

public class BaseService<T> extends BaseRepository<T> implements IBaseService<T> {
}
