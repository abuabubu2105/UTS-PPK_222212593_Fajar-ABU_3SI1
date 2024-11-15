/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license/default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.ukmbulstik.rpc;

import lombok.*;

/**
 * Represents a JSON-RPC response object for the `ukm bulstik` system.
 * Holds protocol version, result, error details, and a request identifier.
 * 
 * @version 1.0
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JsonRpcResponse {

    private String jsonrpc;   // JSON-RPC version
    private Object result;    // Response result
    private Object error;     // Error details if any
    private String id;        // Request identifier

    /**
     * Constructs a JsonRpcResponse with only a result and ID, typically for successful responses.
     * 
     * @param result The result of the JSON-RPC call
     * @param id     The identifier of the JSON-RPC request
     */
    public JsonRpcResponse(Object result, String id) {
        this.result = result;
        this.id = id;
    }
}
