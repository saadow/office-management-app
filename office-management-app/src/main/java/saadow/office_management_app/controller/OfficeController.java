package saadow.office_management_app.controller;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import saadow.office_management_app.entity.Office;
import saadow.office_management_app.service.OfficeService;

@RestController
@RequestMapping("/office")
public class OfficeController {
	private static final Logger LOG = LogManager.getLogger(OfficeController.class);


	@Autowired
	private OfficeService officeService;

	@GetMapping
	public @ResponseBody Set<Office> getOfficeQtyBetween() {
		LOG.debug("getOfficeQtyBetween use getAllOffice");
		return officeService.getAllOffice();
	}

	@PostMapping
	public void addOffice(@Valid @RequestBody Office OfficeRequest) {
		LOG.info("addOffice start, OfficeRequest={}", OfficeRequest);
		officeService.insertOffice(OfficeRequest);
		LOG.info("addOffice end");
	}

	@GetMapping("/{id}")
	public @ResponseBody Office getOfficeById(@PathVariable("id") int id) {
		LOG.info("getOfficeById start, id={}", id);
		Office result = officeService.findOfficeById(BigDecimal.valueOf(id));
		LOG.info("getOfficeById end");
		return result;
	}

	@DeleteMapping("/{id}")
	public void deleteOfficeById(@PathVariable("id") int id) {
		LOG.info("deleteOfficeById start, id={}", id);
		officeService.deleteOffice(BigDecimal.valueOf(id));
		LOG.info("deleteOfficeById end");
	}

	@PutMapping("/{id}")
	public void updateOfficeById(@PathVariable("id") int id, @RequestParam("sales") Integer sales) {
		LOG.info("updateOfficeById start, id={}, sales={}", id, sales);
		Office office = officeService.findOfficeById(BigDecimal.valueOf(id));
		if (Objects.isNull(office)) {
			LOG.warn("updateOfficeById cannot update not existing Office");
		} else {
			office.setSales(BigDecimal.valueOf(sales));
			officeService.updateOffice(office);
		}
		LOG.info("updateOfficeById end");
	}
}
