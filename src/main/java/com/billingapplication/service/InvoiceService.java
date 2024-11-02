package com.billingapplication.service;

import com.billingapplication.model.Invoice;
import com.billingapplication.model.Product;
import com.billingapplication.repo.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.Proxy;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(String invoiceId) {
        return invoiceRepository.findById(invoiceId);
    }

    public Invoice createInvoice(Invoice invoice) {

        double totalAmount = 0.0;

                    for(Product product : invoice.getProducts()){
                        //double productQuantity = Double.parseDouble(product.getQuantity());
                        double productPrice = Double.parseDouble(product.getPrice());
                        double productQuantity = Double.parseDouble(product.getCartQuantity());
                        totalAmount = totalAmount + productPrice*productQuantity;
                    }
                    String invoiceTotalAmount = String.valueOf(totalAmount);
                    invoice.setTotalAmount(invoiceTotalAmount);
        return invoiceRepository.save(invoice);
    }

    public Invoice updateInvoice(String invoiceId, Invoice updatedInvoice) {
        Optional<Invoice> existingInvoiceOpt = invoiceRepository.findById(invoiceId);
        
        if (existingInvoiceOpt.isPresent()) {
            Invoice existingInvoice = existingInvoiceOpt.get();
            existingInvoice.setCustomer(updatedInvoice.getCustomer());
            existingInvoice.setProducts(updatedInvoice.getProducts());
            existingInvoice.setPaymentName(updatedInvoice.getPaymentName());
            existingInvoice.setPaymentNum(updatedInvoice.getPaymentNum());
            existingInvoice.setAddress(updatedInvoice.getAddress());
            existingInvoice.setGstin(updatedInvoice.getGstin());
            existingInvoice.setBillNum(updatedInvoice.getBillNum());
            existingInvoice.setBillDate(updatedInvoice.getBillDate());
            existingInvoice.setTermDueDate(updatedInvoice.getTermDueDate());
            return invoiceRepository.save(existingInvoice);
        } else {
            return null; // Or throw a custom exception if desired
        }
    }

    public void deleteInvoice(String invoiceId) {
        invoiceRepository.deleteById(invoiceId);
    }

    public List<Invoice> getInvoiceByCustomerId(String id){
        return invoiceRepository.findByCustomerCustomerid(id);
    }
}
