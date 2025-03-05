package com.acl.formaliteagricultureapi.converter;


import com.acl.formaliteagricultureapi.dto.RegisterRequest;
import com.acl.formaliteagricultureapi.dto.UserDto;
import com.acl.formaliteagricultureapi.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    /**
     * @param registerRequest
     * @return
     */
    public UserDto convertRequest(RegisterRequest registerRequest) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(registerRequest, UserDto.class);
    }

    public UserDto convertDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDto.class);
    }

    /**
     * Dto vers Entity
     *
     * @param userDto
     * @return
     */
    public User convertEntity(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDto, User.class);
    }

}