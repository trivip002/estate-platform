package com.estate.controller.admin;


import com.estate.constant.SystemConstant;
import com.estate.dto.BuildingDTO;
import com.estate.dto.UserDTO;
import com.estate.service.impl.BuildingService;
import com.estate.service.impl.DistrictService;
import com.estate.service.impl.UserService;
import com.estate.utils.DisplayTagUtils;
import com.estate.utils.MessageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller(value = "buldingOfAdmin")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    @Autowired
    private UserService userService;

    @Autowired
    private DistrictService districtService;

    @RequestMapping(value = "/admin/building/list", method = RequestMethod.GET)
    public ModelAndView listBuldings(@ModelAttribute(SystemConstant.MODEL)BuildingDTO model,
                                     HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        DisplayTagUtils.initSearchBean(request,model);
        Map<String,String> mapType = buildingService.getMaptype();
        List<BuildingDTO> buildingDTOS = new ArrayList<>();
        if(model.getSearchValue() == null){
            Pageable pageable = new PageRequest(model.getPage() - 1, model.getMaxPageItems());
            buildingDTOS = buildingService.getBuilding(pageable);
            model.setTotalItems(buildingService.getTotalItems());
        }else{
            buildingDTOS = buildingService.searchBuildingsByPrioritizeAndUser(model);
            model.setTotalItems(buildingService.getTotalItemsSearch(model));
        }
        model.setListResult(buildingDTOS);
        model.setDistricts(districtService.getDistricts());
        List<String> userDTOS = userService.getAllUserName();
        initMessageResponse(mav, request);
        mav.addObject(SystemConstant.MODEL, model);
        mav.addObject("listUsers", userDTOS);
        mav.addObject("mapType",mapType);
        return mav;
    }
    @RequestMapping(value = "/admin/building/favorite", method = RequestMethod.GET)
    public ModelAndView getFavoriteBuilding(@ModelAttribute(SystemConstant.MODEL)BuildingDTO model,
                                            HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/favorite");
        DisplayTagUtils.initSearchBean(request,model);
        Pageable pageable = new PageRequest(model.getPage() - 1, model.getMaxPageItems());
        List<BuildingDTO> buildingDTOS = buildingService.getfavoriteBuilding(pageable);
        model.setListResult(buildingDTOS);
        model.setTotalItems(buildingService.getTotalFavoriteItems());
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }
    @RequestMapping(value = "/admin/building/edit",method = RequestMethod.GET)
    public ModelAndView updatOrAddBuilding(@RequestParam(value = "id", required = false) String id){
        BuildingDTO buildingDTO = new BuildingDTO();
        if(id != null){
            buildingDTO = buildingService.getOneById(Long.valueOf(id));
        }
        Map<String,String> mapType = buildingService.getMaptype();
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("mapType",mapType);
        mav.addObject(SystemConstant.MODEL, buildingDTO);
        return mav;
    }
    @RequestMapping(value = "/admin/building/{id}/detail",method = RequestMethod.GET)
    public ModelAndView viewDetailBuilding(@PathVariable("id") long id )
    {
        ModelAndView mav = new ModelAndView("admin/building/detail");
        BuildingDTO buildingDTO = buildingService.getOneById(id);
        mav.addObject(SystemConstant.MODEL,buildingDTO);
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
