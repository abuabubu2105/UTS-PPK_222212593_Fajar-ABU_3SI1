/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license/default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.ukmbulstik.rpc;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a JSON-RPC request object in the `ukm bulstik` system.
 * Includes fields for protocol version, method name, parameters, and ID.
 * 
 * @version 1.0
 */
@Setter
@Getter
public class JsonRpcRequest {
    private String jsonrpc;  // JSON-RPC version
    private String method;   // Method name
    private JsonNode params; // Parameters as a JSON object
    private String id;       // Request identifier
}
