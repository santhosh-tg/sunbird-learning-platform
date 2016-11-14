package com.ilimi.taxonomy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ilimi.common.controller.BaseController;
import com.ilimi.common.dto.Response;
import com.ilimi.common.logger.LogHelper;
import com.ilimi.taxonomy.mgr.IAuditHistoryManager;

@Controller
@RequestMapping("/v1/audit")
public class AuditHistoryController extends BaseController {

	private static LogHelper LOGGER = LogHelper.getInstance(AuditHistoryController.class.getName());

	@Autowired
	private IAuditHistoryManager auditHistoryManager;

	@RequestMapping(value = "/{graphId:.+}/all", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Response> getAll(@PathVariable(value = "graphId") String graphId,
			@RequestParam(value = "start", required = false) String startTime,
			@RequestParam(value = "end", required = false) String endTime,
			@RequestHeader(value = "user-id") String userId) {
		String apiId = "audit_history.getAll";

		LOGGER.info("get all AuditHistory | GraphId: " + graphId + " | TimeStamp1: " + startTime + " | Timestamp2: "
				+ endTime);
		try {
			Response response = auditHistoryManager.getAuditHistory(graphId, startTime, endTime);
			LOGGER.info("Find Item | Response: " + response);
			return getResponseEntity(response, apiId, null);
		} catch (Exception e) {
			LOGGER.error("Find Item | Exception: " + e.getMessage(), e);
			return getExceptionResponseEntity(e, apiId, null);
		}
	}

	@RequestMapping(value = "/history/{graphId:.+}/{objectId:.+}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Response> getById(@PathVariable(value = "graphId") String graphId,
			@PathVariable(value = "objectId") String objectId,
			@RequestParam(value = "start", required = false) String startTime,
			@RequestParam(value = "end", required = false) String endTime,
			@RequestHeader(value = "user-id") String userId) {
		String apiId = "audit_history.getById";

		LOGGER.info("get AuditHistory By ObjectId | GraphId: " + graphId + " | TimeStamp1: " + startTime
				+ " | Timestamp2: " + endTime + " | ObjectId: " + objectId);
		try {
			Response response = auditHistoryManager.getAuditHistoryById(graphId, objectId, startTime, endTime);
			LOGGER.info("Find Item | Response: " + response);
			return getResponseEntity(response, apiId, null);
		} catch (Exception e) {
			LOGGER.error("Find Item | Exception: " + e.getMessage(), e);
			return getExceptionResponseEntity(e, apiId, null);
		}
	}

	@RequestMapping(value = "/{graphId:.+}/{objectType:.+}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Response> getByType(@PathVariable(value = "graphId") String graphId,
			@PathVariable(value = "objectType") String objectType,
			@RequestParam(value = "start", required = false) String startTime,
			@RequestParam(value = "end", required = false) String endTime,
			@RequestHeader(value = "user-id") String userId) {
		String apiId = "audit_history.getByType";

		LOGGER.info("get AuditHistory By ObjectType | GraphId: " + graphId + " | TimeStamp1: " + startTime
				+ " | Timestamp2: " + endTime + " | ObjectType: " + objectType);
		try {
			Response response = auditHistoryManager.getAuditHistoryByType(graphId, objectType, startTime, endTime);
			LOGGER.info("Find Item | Response: " + response);
			return getResponseEntity(response, apiId, null);
		} catch (Exception e) {
			LOGGER.error("Find Item | Exception: " + e.getMessage(), e);
			return getExceptionResponseEntity(e, apiId, null);
		}
	}

	@RequestMapping(value = "/details/{auditId:.+}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Response> getLogRecord(@PathVariable(value = "auditId") String auditId,
			@RequestParam(value = "start", required = false) String startTime,
			@RequestParam(value = "end", required = false) String endTime,
			@RequestHeader(value = "user-id") String userId) {
		String apiId = "audit_history.getLogRecordByAuditId";

		LOGGER.info("get AuditHistory By auditId | TimeStamp1: " + startTime + " | Timestamp2: " + endTime
				+ " | auditId: " + auditId);
		try {
			Response response = auditHistoryManager.getAuditLogRecordByAuditId(auditId, startTime, endTime);
			LOGGER.info("Find Item | Response: " + response);
			return getResponseEntity(response, apiId, null);
		} catch (Exception e) {
			LOGGER.error("Find Item | Exception: " + e.getMessage(), e);
			return getExceptionResponseEntity(e, apiId, null);
		}
	}

}
