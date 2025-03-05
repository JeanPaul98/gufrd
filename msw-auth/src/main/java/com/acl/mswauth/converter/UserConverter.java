package com.acl.mswauth.converter;


import com.acl.mswauth.dto.register.RegisterDto;
import com.acl.mswauth.dto.register.RegisterStructureDto;
import com.acl.mswauth.dto.user.UserDto;
import com.acl.mswauth.model.User;
import com.acl.mswauth.request.RegisterRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    /**
     *
     * @param registerRequest
     * @return
     */
    public UserDto convertRequest(RegisterRequest registerRequest){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(registerRequest, UserDto.class);
    }

    public UserDto convertToRegister(RegisterDto request){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(request, UserDto.class);
    }

    public UserDto convertToRegisterStructure(RegisterStructureDto request){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(request, UserDto.class);
    }

    public UserDto convertDto(User user){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDto.class);
    }
    /**
     * Dto vers Entity
     * @param userDto
     * @return
     */
    public User convertEntity(UserDto userDto){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDto, User.class);
    }

}