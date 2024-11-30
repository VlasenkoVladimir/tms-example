package effectiveMobile.com.taskManagementSystem.controllers.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Abstract parent class for rest endpoints
 */
@RestController
@RequestMapping(value = "/api/rest")
@Validated
public abstract class GenericRestController {
}