package com.recruitment.task.holidaychecker.web.admin;

import com.recruitment.task.holidaychecker.config.entity.HolidayCheckConfig;
import com.recruitment.task.holidaychecker.config.repository.HolidayCheckConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class HolidayCheckAdminController {

    private static final String HOLIDAY_CHECK_CONFIGS = "holidayCheckConfigs";

    private static final String MESSAGE = "message";

    @Autowired
    private HolidayCheckConfigRepository holidayCheckConfigRepository;

    @RequestMapping(value = "/allHolidayCheckConfigs", method = RequestMethod.GET)
    public ModelAndView getAllHolidayCheckConfigs() {
        ModelAndView modelAndView = createModelAndView();
        modelAndView.addObject(HOLIDAY_CHECK_CONFIGS, holidayCheckConfigRepository.getAllHolidayCheckConfigs());

        return modelAndView;
    }

    @RequestMapping(value = "/updateHolidayCheckConfig", method = RequestMethod.POST)
    public ModelAndView updateHolidayCheckConfig(@RequestParam String key, @RequestParam String value) {
        ModelAndView modelAndView = createModelAndView();

        String holidayCheckConfigValue = holidayCheckConfigRepository.getHolidayCheckConfigValueByKey(key);

        if (!StringUtils.isEmpty(holidayCheckConfigValue)) {
            holidayCheckConfigRepository.updateHolidayCheckConfig(createHolidayCheckConfig(key, value));
            modelAndView.addObject(MESSAGE, "Config updated");
        } else {
            modelAndView.addObject(MESSAGE, "Config key '" + key + "' does not exist");
        }

        return modelAndView;
    }

    private ModelAndView createModelAndView() {
        return new ModelAndView(HOLIDAY_CHECK_CONFIGS);
    }

    private HolidayCheckConfig createHolidayCheckConfig(String key, String value) {
        HolidayCheckConfig holidayCheckConfig = new HolidayCheckConfig();
        holidayCheckConfig.setKey(key);
        holidayCheckConfig.setValue(value);

        return holidayCheckConfig;
    }
}