package com.recruitment.task.holidaychecker.web.admin;

import com.recruitment.task.holidaychecker.config.entity.HolidayCheckConfig;
import com.recruitment.task.holidaychecker.config.repository.HolidayCheckConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class HolidayCheckAdminController {

    private static final String HOLIDAY_CHECK_CONFIGS = "holidayCheckConfigs";

    private static final String MESSAGE = "message";

    @Autowired
    private HolidayCheckConfigRepository holidayCheckConfigRepository;

    @RequestMapping(value = "/allHolidayCheckConfigs", method = RequestMethod.GET)
    public ModelAndView getAllHolidayCheckConfigs(@ModelAttribute String message) {
        ModelAndView modelAndView = new ModelAndView(HOLIDAY_CHECK_CONFIGS);
        modelAndView.addObject(HOLIDAY_CHECK_CONFIGS, holidayCheckConfigRepository.getAllHolidayCheckConfigs());

        if (!StringUtils.isEmpty(message)) {
            modelAndView.addObject(MESSAGE, message);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/updateHolidayCheckConfig", method = RequestMethod.POST)
    public RedirectView updateHolidayCheckConfig(@RequestParam String key, @RequestParam String value, RedirectAttributes redirectAttributes) {
        holidayCheckConfigRepository.updateHolidayCheckConfig(createHolidayCheckConfig(key, value));

        redirectAttributes.addFlashAttribute(MESSAGE, generateMessage(key, value));

        return new RedirectView("/admin/allHolidayCheckConfigs");
    }

    private HolidayCheckConfig createHolidayCheckConfig(String key, String value) {
        HolidayCheckConfig holidayCheckConfig = new HolidayCheckConfig();
        holidayCheckConfig.setKey(key);
        holidayCheckConfig.setValue(value);

        return holidayCheckConfig;
    }

    private String generateMessage(String key, String value) {
        return "Key '" + key + "' successfully updated with '" + value + "' value";
    }
}