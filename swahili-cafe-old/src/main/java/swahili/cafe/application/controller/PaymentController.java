package swahili.cafe.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swahili.cafe.application.model.Payment;
import swahili.cafe.application.model.dto.PaymentRequestDTO;
import swahili.cafe.application.service.PaymentService;


import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;


    //    add Payment
    @PostMapping("/makePayment/expert/{expertId}/book/{bookId}")
    public ResponseEntity<Payment> makePayment(@RequestBody PaymentRequestDTO requestDTO, @PathVariable long expertId, @PathVariable long bookId) {
        Payment makePayment = paymentService.makePayment(requestDTO.getAmount(), expertId, bookId);
        return new ResponseEntity<>(makePayment, HttpStatus.CREATED);
    }


    //    update Payment
    @PutMapping("/update/{paymentId}")
    public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment, @PathVariable long paymentId) {
        Payment updatePayment = paymentService.updatePayment(payment.getPaymentAmount(), payment.getStatus(), paymentId);
        return new ResponseEntity<>(updatePayment, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }


    @GetMapping("/all/expert/{username}")
    public ResponseEntity<List<Payment>> getAllPaymentsByExpertUsername(@PathVariable String username) {
        List<Payment> payments = paymentService.getAllPaymentsByExpertUsername(username);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }



    @GetMapping("/get-total-payments")
    public ResponseEntity<Long> getTotalPayments() {
        Long totalPayments = paymentService.getTotalPayments();
        return new ResponseEntity<>(totalPayments, HttpStatus.OK);
    }



    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> findPayment(@PathVariable long paymentId) {
        Payment booking = paymentService.findPayment(paymentId);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{paymentId}")
    public ResponseEntity<?> deletePayment(@PathVariable long paymentId) {
        paymentService.deletePayment(paymentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
