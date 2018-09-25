package com.estate.controller.admin;

import com.estate.constant.SystemConstant;
import com.estate.dto.BuildingDTO;
import com.estate.repository.UserRepository;
import com.estate.service.IBuildingService;
import com.estate.service.IDistrictService;
import com.estate.service.IUserService;
import com.estate.utils.DisplayTagUtils;
import com.estate.utils.MessageUtil;
import com.estate.utils.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class BuildingController {

    @Autowired
    private IBuildingService buildingService;


    @Autowired
    private IUserService userService;

    @Autowired
    private IDistrictService districtService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/admin/building/list", method = RequestMethod.GET)
    public ModelAndView getBuildings(@ModelAttribute(SystemConstant.MODEL) BuildingDTO model,
                                     HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        DisplayTagUtils.initSearchBean(request, model);
        model.setMaxPageItems(5);
        List<BuildingDTO> buildings = buildingService.searchBuildingsAssignment(model,false);
        model.setTotalItems(buildingService.getTotalItemsAssignment(model,false));
        model.setListResult(buildings);
        model.setDistricts(districtService.getDistricts());
        initMessageResponse(mav, request);
        mav.addObject(SystemConstant.MODEL, model);
        mav.addObject(SystemConstant.MAP_TYPES, buildingService.getBuildingTypes());
        mav.addObject(SystemConstant.MAP_USERS, userService.getStaffs());
        return mav;
    }

    @RequestMapping(value = "/admin/building/list-priority", method = RequestMethod.GET)
    public ModelAndView getBuildingsPriority(@ModelAttribute(SystemConstant.MODEL) BuildingDTO model,
                                     HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/list-priority");
        DisplayTagUtils.initSearchBean(request, model);
        model.setMaxPageItems(5);
        List<BuildingDTO> buildings = buildingService.searchBuildingsByPriority(model) ;
        model.setTotalItems(buildingService.getTotalItemsPriority(model));
        model.setListResult(buildings);
        model.setDistricts(districtService.getDistricts());
        initMessageResponse(mav, request);
        mav.addObject(SystemConstant.MODEL, model);
        mav.addObject(SystemConstant.MAP_TYPES, buildingService.getBuildingTypes());
        mav.addObject(SystemConstant.MAP_USERS, userService.getStaffs());
        return mav;
    }

    @RequestMapping(value = "/admin/building/list/assignment", method = RequestMethod.GET)
    public ModelAndView getBuildingsAssignment(@ModelAttribute(SystemConstant.MODEL) BuildingDTO model,
                                     HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/list-assignment");
        DisplayTagUtils.initSearchBean(request, model);
        model.setMaxPageItems(5);
        List<BuildingDTO> buildings = buildingService.searchBuildingsAssignment(model,true);
        model.setTotalItems(buildingService.getTotalItemsAssignment(model,true));
        model.setListResult(buildings);
        model.setDistricts(districtService.getDistricts());
        initMessageResponse(mav, request);
        mav.addObject(SystemConstant.MODEL, model);
        mav.addObject(SystemConstant.MAP_TYPES, buildingService.getBuildingTypes());
        mav.addObject(SystemConstant.MAP_USERS, userService.getStaffs());
        return mav;
    }


    @RequestMapping(value = "/admin/building/edit", method = RequestMethod.GET)
    public ModelAndView getBuildingById(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        if (id != null) { //edit
            initMessageResponse(mav, request);
            mav.addObject(SystemConstant.MODEL, buildingService.findBuildingById(id));
        } else { // insert
            BuildingDTO buildingDTO = new BuildingDTO();
            buildingDTO.setDistricts(districtService.getDistricts());
            mav.addObject(SystemConstant.MODEL, buildingDTO);
        }
        mav.addObject(SystemConstant.MAP_TYPES, buildingService.getBuildingTypes());
        //mav.addObject(SystemConstant.MAP_USERS, userService.getUsers());
        return mav;
    }

    @RequestMapping(value = "/admin/building/detail/{id}", method = RequestMethod.GET)
    public ModelAndView getbuildingDetailById(@PathVariable("id") long id) {
        ModelAndView mav = new ModelAndView("admin/building/detail");
        mav.addObject(SystemConstant.MODEL, buildingService.findBuildingById(id));
        return mav;
    }

    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = MessageUtil.getMessageResponse(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }


}