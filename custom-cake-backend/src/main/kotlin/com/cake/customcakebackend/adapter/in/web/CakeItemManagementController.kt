package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.application.port.`in`.CakeItemManagementUseCase
import com.cake.customcakebackend.application.port.`in`.StoreManagementUseCase
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping(
    path = ["/operator"]
)
class CakeItemManagementController(
    private val cakeItemManagementUseCase: CakeItemManagementUseCase,
    private val storeManagementUseCase: StoreManagementUseCase

) {

    @GetMapping("/cake-item/{cakeItemId}")
    fun cakeItemInfo(
        @RequestParam operatorId: Long,
        @RequestParam storeId: Long,
        @PathVariable cakeItemId: Long,
        model: Model
    ): String {
        return "cake-item-detail"
    }

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
            val cakeItemResponseList = cakeItemManagementUseCase.loadCakeItemList(storeId)
            model.addAttribute("cakeItemResponseList", cakeItemResponseList)
        }
        return "cake-item-management"
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

    /**
     * deleteCakeItem method
     * : 케이크 옵션 삭제
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/04/09
     **/
    @DeleteMapping("/cake-item/{cakeItemId}")
    fun deleteCakeItem(
        @RequestParam operatorId: Long,
        @RequestParam storeId: Long,
        @PathVariable cakeItemId: Long,
        redirectAttributes: RedirectAttributes
    ): String {
        cakeItemManagementUseCase.deleteCakeItem(cakeItemId)

        addAttributeToModel("operatorId", operatorId, redirectAttributes)
        addAttributeToModel("storeId", storeId, redirectAttributes)

        return "redirect:/operator/cake-item"
    }

    private fun addAttributeToModel(attributeName: String, id: Long, model: Model) =
        model.addAttribute(attributeName, id)
}