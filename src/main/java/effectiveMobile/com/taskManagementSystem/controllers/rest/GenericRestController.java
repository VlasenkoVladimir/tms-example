package effectiveMobile.com.taskManagementSystem.controllers.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/rest")
@Validated
public abstract class GenericRestController {
}