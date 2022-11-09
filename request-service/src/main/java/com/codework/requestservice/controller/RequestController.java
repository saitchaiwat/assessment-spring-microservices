package com.codework.requestservice.controller;

import com.codework.requestservice.dto.RequestDetailDTO;
import com.codework.requestservice.dto.RequestViewDTO;
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

    @CrossOrigin("*")
    @Operation(description = "Sort direction: ASC or DESC")
    @RequestMapping(value = "/staff/getAllRemainingRequestsByStaff", method = RequestMethod.GET)
    public ResponseEntity<Page<RequestViewDTO>> getAllRemainingRequestsByStaff(@RequestParam(name = "staffId") Long staffId,
                                                                               @RequestParam(name = "keyword", required = false) String keyword,
                                                                               @RequestParam(name = "offset", defaultValue = "0") int offset,
                                                                               @RequestParam(name = "pageSize", defaultValue = "25") int pageSize,
                                                                               @RequestParam(name = "sortCol", defaultValue = "title") String sortCol,
                                                                               @RequestParam(name = "sortDir", defaultValue = "ASC") String sortDir) {
        try {
            Page<RequestViewDTO> requests = requestService.getAllRemainingRequestsByStaff(staffId, keyword, sortCol, sortDir, offset, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(requests);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @CrossOrigin("*")
    @Operation(description = "Sort direction: ASC or DESC")
    @RequestMapping(value = "/staff/getAllRequestsByStaff", method = RequestMethod.GET)
    public ResponseEntity<Page<RequestViewDTO>> getAllRequestsByStaff(@RequestParam(name = "staffId") Long staffId,
                                                                      @RequestParam(name = "keyword", required = false) String keyword,
                                                                      @RequestParam(name = "offset", defaultValue = "0") int offset,
                                                                      @RequestParam(name = "pageSize", defaultValue = "25") int pageSize,
                                                                      @RequestParam(name = "sortCol", defaultValue = "title") String sortCol,
                                                                      @RequestParam(name = "sortDir", defaultValue = "ASC") String sortDir) {
        try {
            Page<RequestViewDTO> requests = requestService.getAllRequestsByStaff(staffId, keyword, sortCol, sortDir, offset, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(requests);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @CrossOrigin("*")
    @Operation(description = "Sort direction: ASC or DESC")
    @RequestMapping(value = "/manager/getAllRequestsByManager", method = RequestMethod.GET)
    public ResponseEntity<Page<RequestViewDTO>> getAllRequestsByManager(@RequestParam(name = "keyword", required = false) String keyword,
                                                                        @RequestParam(name = "offset", defaultValue = "0") int offset,
                                                                        @RequestParam(name = "pageSize", defaultValue = "25") int pageSize,
                                                                        @RequestParam(name = "sortCol", defaultValue = "title") String sortCol,
                                                                        @RequestParam(name = "sortDir", defaultValue = "ASC") String sortDir) {
        try {
            Page<RequestViewDTO> requests = requestService.getAllRequestsByManager(keyword, sortCol, sortDir, offset, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(requests);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @CrossOrigin("*")
    @Operation(description = "Sort direction: ASC or DESC")
    @RequestMapping(value = "/manager/getAllRemainingRequestsByManager", method = RequestMethod.GET)
    public ResponseEntity<Page<RequestViewDTO>> getAllRemainingRequestsByManager(@RequestParam(name = "keyword", required = false) String keyword,
                                                                                 @RequestParam(name = "offset", defaultValue = "0") int offset,
                                                                                 @RequestParam(name = "pageSize", defaultValue = "25") int pageSize,
                                                                                 @RequestParam(name = "sortCol", defaultValue = "title") String sortCol,
                                                                                 @RequestParam(name = "sortDir", defaultValue = "ASC") String sortDir) {
        try {
            Page<RequestViewDTO> requests = requestService.getAllRemainingRequestsByManager(keyword, sortCol, sortDir, offset, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(requests);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/getRequestDetail/{id}", method = RequestMethod.GET)
    public ResponseEntity<RequestDetailDTO> getRequestDetail(@PathVariable Long id) {
        try {
            RequestDetailDTO request = requestService.getRequestDetail(id);
            return ResponseEntity.status(HttpStatus.OK).body(request);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/staff/saveDraft", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> saveDraft(
            @RequestParam(name = "customerId") Long customerId,
            @RequestParam(name = "requesterId") Long requesterId,
            @RequestParam(name = "managerId") Long managerId,
            @RequestParam(name = "requestTitle") String requestTitle,
            @RequestParam(name = "requestDetail") String requestDetail,
            @RequestPart(name = "requestImages") MultipartFile[] requestImages
    ) {
        try {
            requestService.saveDraft(customerId, requesterId, managerId, requestTitle, requestDetail, requestImages);

            return ResponseEntity.status(HttpStatus.OK).body("Draft saved Successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/staff/sendRequest", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> sendRequest(
            @RequestParam(name = "customerId") Long customerId,
            @RequestParam(name = "requesterId") Long requesterId,
            @RequestParam(name = "managerId") Long managerId,
            @RequestParam(name = "requestTitle") String requestTitle,
            @RequestParam(name = "requestDetail") String requestDetail,
            @RequestPart(name = "requestImages") MultipartFile[] requestImages
    ) {
        try {
            requestService.sendRequest(customerId, requesterId, managerId, requestTitle, requestDetail, requestImages);

            return ResponseEntity.status(HttpStatus.OK).body("Request send Successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/staff/sendDraftRequest/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<String> sendDraftRequest(@PathVariable Long id) {
        try {
            requestService.sendDraftRequest(id);
            return ResponseEntity.status(HttpStatus.OK).body("Request send Successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/manager/approveRequest/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<String> approveRequest(@PathVariable Long id) {
        try {
            requestService.approveRequest(id);
            return ResponseEntity.status(HttpStatus.OK).body("Request approved");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/manager/rejectRequest", method = RequestMethod.PATCH)
    public ResponseEntity<String> rejectRequest(@RequestParam(name = "id") Long id, @RequestParam(name = "reason") String reason) {
        try {
            requestService.rejectRequest(id, reason);
            return ResponseEntity.status(HttpStatus.OK).body("Request rejected");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/manager/modifyDetail", method = RequestMethod.PATCH)
    public ResponseEntity<String> modifyDetail(@RequestParam(name = "id") Long id, @RequestParam(name = "detail") String detail) {
        try {
            requestService.modifyDetail(id, detail);
            return ResponseEntity.status(HttpStatus.OK).body("Detail modified");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }
}
