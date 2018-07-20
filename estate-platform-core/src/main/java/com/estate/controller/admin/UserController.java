package com.estate.controller.admin;

import com.estate.constant.SystemConstant;
import com.estate.dto.UserDTO;
import com.estate.service.impl.RoleService;
import com.estate.service.impl.UserService;
import com.estate.utils.DisplayTagUtils;
import com.estate.utils.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller(value = "UserControllerOfAdmin")
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/admin/user/list", method = RequestMethod.GET)
    public ModelAndView getListUser(@ModelAttribute(SystemConstant.MODEL)UserDTO model,
                                HttpServletRequest request)
    {
        ModelAndView mav = new ModelAndView("admin/user/list");
        DisplayTagUtils.initSearchBean(request,model);
        Pageable pageable = new PageRequest(model.getPage() - 1, model.getMaxPageItems());
        List<UserDTO> userDTOS = userService.getUsers(model.getSearchValue(), pageable );
        model.setListResult(userDTOS);
        model.setTotalItems(userService.getTotalItem(model.getSearchValue()));
        long authorize = SecurityUtils.getPrincipal().getId();
        initMessageResponse(mav, request);
        mav.addObject(SystemConstant.MODEL, model);
        mav.addObject(SystemConstant.PRESENT_USER,authorize);
        return mav;
    }

    @RequestMapping(value = "/admin/user/edit",method = RequestMethod.GET)
    public ModelAndView updatNewUser(@RequestParam(value = "id", required = false) String id,
                                     HttpServletRequest request)
    {
        ModelAndView mav = new ModelAndView("admin/user/edit");
        UserDTO userDTO = new UserDTO();
        if(id != null){
            userDTO = userService.getById(Long.valueOf(id));
        }
        userDTO.setRolesName(roleService.getAllName());
        initMessageResponse(mav, request);
        mav.addObject(SystemConstant.MODEL,userDTO);
        return mav;
    }

    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = userService.getMessageResponse(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }
}
