package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.application.port.`in`.CakeItemManagementUseCase
import com.cake.customcakebackend.application.port.`in`.StoreManagementUseCase
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping(
    path = ["/operator"]
)
class CakeItemManagementController(
    private val cakeItemManagementUseCase: CakeItemManagementUseCase,
    private val storeManagementUseCase: StoreManagementUseCase

) {

    @GetMapping("/cake-item")
    fun cakeItemList(
        @RequestParam operatorId: Long,
        @RequestParam storeId: Long,
        model: Model
    ): String {
        addAttributeToModel("operatorId", operatorId, model)
        addAttributeToModel("storeId", storeId, model)

        // TODO check (operatorId.storeId == storeID)

        // 매장 check
        val hasStore = storeManagementUseCase.hasStore(operatorId)
        model.addAttribute("hasStore", hasStore)
        if (hasStore) {
            val cakeItemList = cakeItemManagementUseCase.loadCakeItemList(storeId)
            model.addAttribute("cakeItemList", cakeItemList)
        }
        return "cake-option-management"
    }

    @GetMapping("/cake-item/form")
    fun addCakeItemForm(
        @RequestParam operatorId: Long,
        @RequestParam storeId: Long,
        model: Model
    ): String {
        addAttributeToModel("operatorId", operatorId, model)
        addAttributeToModel("storeId", storeId, model)
        // 매장 check
        val hasStore = storeManagementUseCase.hasStore(operatorId)
        model.addAttribute("hasStore", hasStore)

        return "cake-item-add"
    }

    private fun addAttributeToModel(attributeName: String, id: Long, model: Model) =
        model.addAttribute(attributeName, id)
}