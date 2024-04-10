package com.example.routes

import com.example.models.Customer
import com.example.models.customers
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.customerRouting(){
    route("/customer"){
        get {
            if(customers.isNotEmpty()){
                call.respond(customers)
            }else{
                call.respondText("No customer found", status = HttpStatusCode.OK)
            }
        }

        get ( "{id?}" ){
            val id = call.parameters["id"] ?: return@get call.respondText("Missing ID", status = HttpStatusCode.BadRequest)
            val customer = customers.find{ it.id == id } ?: return@get call.respondText("No Customer was found with this id $id", status = HttpStatusCode.NotFound)
            call.respond(customer)
        }

        post {
            val customer = call.receive<Customer>()
            customers.add(customer)
            call.respondText("Customer created successfully", status = HttpStatusCode.Created)
        }

        delete ("{id?}"){
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if(customers.removeIf{it.id == id}){
                call.respondText("Customer removed successfully", status = HttpStatusCode.Accepted)
            }else{
                call.respondText("Not found", status = HttpStatusCode.NotFound)
            }
        }
    }
}