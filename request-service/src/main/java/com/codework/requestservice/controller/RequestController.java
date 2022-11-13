package com.codework.requestservice.controller;

import com.codework.requestservice.client.UserClient;
import com.codework.requestservice.client.UserModel;
import com.codework.requestservice.dto.RequestDetailDTO;
import com.codework.requestservice.dto.RequestViewDTO;
import com.codework.requestservice.response.ResponseHandler;
import com.codework.requestservice.service.RequestServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/request")
public class RequestController {
    @Autowired
    private RequestServiceImpl requestService;

    @Autowired
    private UserClient userClient;

    @CrossOrigin("*")
    @Operation(description = "Sort direction: ASC or DESC")
    @RequestMapping(value = "/staff/getAllRemainingRequestsByStaff", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllRemainingRequestsByStaff(@RequestParam(name = "staffId") Long staffId,
                                                          @RequestParam(name = "keyword", required = false) String keyword,
                                                          @RequestParam(name = "offset", defaultValue = "0") int offset,
                                                          @RequestParam(name = "pageSize", defaultValue = "25") int pageSize,
                                                          @RequestParam(name = "sortCol", defaultValue = "title") String sortCol,
                                                          @RequestParam(name = "sortDir", defaultValue = "ASC") String sortDir) {
        try {
            UserModel staffUser = userClient.getStaffById(staffId);
            if (staffUser == null) {
                return ResponseHandler.generateResponse("Staff not found", HttpStatus.NOT_FOUND, null);
            }

            Page<RequestViewDTO> requests = requestService.getAllRemainingRequestsByStaff(staffId, keyword, sortCol, sortDir, offset, pageSize);

            return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, requests);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }

    @CrossOrigin("*")
    @Operation(description = "Sort direction: ASC or DESC")
    @RequestMapping(value = "/staff/getAllRequestsByStaff", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllRequestsByStaff(@RequestParam(name = "staffId") Long staffId,
                                                                      @RequestParam(name = "keyword", required = false) String keyword,
                                                                      @RequestParam(name = "offset", defaultValue = "0") int offset,
                                                                      @RequestParam(name = "pageSize", defaultValue = "25") int pageSize,
                                                                      @RequestParam(name = "sortCol", defaultValue = "title") String sortCol,
                                                                      @RequestParam(name = "sortDir", defaultValue = "ASC") String sortDir) {
        try {
            UserModel staffUser = userClient.getStaffById(staffId);
            if (staffUser == null) {
                return ResponseHandler.generateResponse("Staff not found", HttpStatus.NOT_FOUND, null);
            }

            Page<RequestViewDTO> requests = requestService.getAllRequestsByStaff(staffId, keyword, sortCol, sortDir, offset, pageSize);

            return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, requests);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }

    @CrossOrigin("*")
    @Operation(description = "Sort direction: ASC or DESC")
    @RequestMapping(value = "/manager/getAllRequestsByManager", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllRequestsByManager(@RequestParam(name = "keyword", required = false) String keyword,
                                                                        @RequestParam(name = "offset", defaultValue = "0") int offset,
                                                                        @RequestParam(name = "pageSize", defaultValue = "25") int pageSize,
                                                                        @RequestParam(name = "sortCol", defaultValue = "title") String sortCol,
                                                                        @RequestParam(name = "sortDir", defaultValue = "ASC") String sortDir) {
        try {
            Page<RequestViewDTO> requests = requestService.getAllRequestsByManager(keyword, sortCol, sortDir, offset, pageSize);

            return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, requests);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }

    @CrossOrigin("*")
    @Operation(description = "Sort direction: ASC or DESC")
    @RequestMapping(value = "/manager/getAllRemainingRequestsByManager", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllRemainingRequestsByManager(@RequestParam(name = "keyword", required = false) String keyword,
                                                                                 @RequestParam(name = "offset", defaultValue = "0") int offset,
                                                                                 @RequestParam(name = "pageSize", defaultValue = "25") int pageSize,
                                                                                 @RequestParam(name = "sortCol", defaultValue = "title") String sortCol,
                                                                                 @RequestParam(name = "sortDir", defaultValue = "ASC") String sortDir) {
        try {
            Page<RequestViewDTO> requests = requestService.getAllRemainingRequestsByManager(keyword, sortCol, sortDir, offset, pageSize);

            return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, requests);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/getRequestDetail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getRequestDetail(@PathVariable Long id) {
        try {
            RequestDetailDTO request = requestService.getRequestDetail(id);

            return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, request);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/staff/saveDraft", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> saveDraft(
            @RequestParam(name = "customerId") Long customerId,
            @RequestParam(name = "requesterId") Long requesterId,
            @RequestParam(name = "managerId") Long managerId,
            @RequestParam(name = "requestTitle") String requestTitle,
            @RequestParam(name = "requestDetail") String requestDetail,
            @RequestPart(name = "requestImages") MultipartFile[] requestImages
    ) {
        try {
            UserModel customerUser = userClient.getCustomerById(customerId);
            if (customerUser == null) {
                return ResponseHandler.generateResponse("Customer not found", HttpStatus.NOT_FOUND, null);
            }

            UserModel staffUser = userClient.getStaffById(requesterId);
            if (staffUser == null) {
                return ResponseHandler.generateResponse("Staff not found", HttpStatus.NOT_FOUND, null);
            }

            UserModel managerUser = userClient.getManagerById(managerId);
            if (managerUser == null) {
                return ResponseHandler.generateResponse("Manager not found", HttpStatus.NOT_FOUND, null);
            }

            requestService.saveDraft(customerId, requesterId, managerId, requestTitle, requestDetail, requestImages);

            return ResponseHandler.generateResponse("Draft saved Successfully", HttpStatus.CREATED);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/staff/sendRequest", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> sendRequest(
            @RequestParam(name = "customerId") Long customerId,
            @RequestParam(name = "requesterId") Long requesterId,
            @RequestParam(name = "managerId") Long managerId,
            @RequestParam(name = "requestTitle") String requestTitle,
            @RequestParam(name = "requestDetail") String requestDetail,
            @RequestPart(name = "requestImages") MultipartFile[] requestImages
    ) {
        try {
            UserModel customerUser = userClient.getCustomerById(customerId);
            if (customerUser == null) {
                return ResponseHandler.generateResponse("Customer not found", HttpStatus.NOT_FOUND, null);
            }

            UserModel staffUser = userClient.getStaffById(requesterId);
            if (staffUser == null) {
                return ResponseHandler.generateResponse("Staff not found", HttpStatus.NOT_FOUND, null);
            }

            UserModel managerUser = userClient.getManagerById(managerId);
            if (managerUser == null) {
                return ResponseHandler.generateResponse("Manager not found", HttpStatus.NOT_FOUND, null);
            }

            requestService.sendRequest(customerId, requesterId, managerId, requestTitle, requestDetail, requestImages);

            return ResponseHandler.generateResponse("Request send Successfully", HttpStatus.CREATED);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/staff/sendDraftRequest/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Object> sendDraftRequest(@PathVariable Long id) {
        try {
            requestService.sendDraftRequest(id);

            return ResponseHandler.generateResponse("Request send Successfully", HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/manager/approveRequest/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Object> approveRequest(@PathVariable Long id) {
        try {
            requestService.approveRequest(id);

            return ResponseHandler.generateResponse("Request approved", HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/manager/rejectRequest", method = RequestMethod.PATCH)
    public ResponseEntity<Object> rejectRequest(@RequestParam(name = "id") Long id, @RequestParam(name = "reason") String reason) {
        try {
            requestService.rejectRequest(id, reason);

            return ResponseHandler.generateResponse("Request rejected", HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/manager/modifyDetail", method = RequestMethod.PATCH)
    public ResponseEntity<Object> modifyDetail(@RequestParam(name = "id") Long id, @RequestParam(name = "detail") String detail) {
        try {
            requestService.modifyDetail(id, detail);

            return ResponseHandler.generateResponse("Detail modified", HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
