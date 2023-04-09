package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.application.port.`in`.CakeOptionManagementUseCase
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping(
    path = ["/operator/cake-option"]
)
class CakeOptionManagementController(
    private val cakeOptionManagementUseCase: CakeOptionManagementUseCase
) {

    /**
     * cakeOptionList method
     * : 해당 매장의 모든 option(1~3) 리스트
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/04/09
    **/
    @GetMapping("")
    fun cakeOptionList(
        @RequestParam storeId: Long,
        model: Model
    ): String {
        // TODO check (operatorId.storeId == storeID)
        val allCakeOptionMap = cakeOptionManagementUseCase.loadAllCakeOptionList(storeId)

        addStoreIdToModel(storeId, model)
        model.addAttribute("allCakeOptionMap", allCakeOptionMap)
        return "cake-option-management"
    }

    @GetMapping("{cakeOptionId}")
    fun cakeOptionInfo(
        @RequestParam storeId: Long,
        @RequestParam cakeOptionType: Int,
        @PathVariable cakeOptionId: Long,
        model: Model
    ): String {
        addStoreIdToModel(storeId, model)
        return "cake-option-detail"
    }

    @GetMapping("/form")
    fun addCakeOptionForm(
        @RequestParam storeId: Long,
        @RequestParam cakeOptionType: Int,
        model: Model): String {
        addStoreIdToModel(storeId, model)
        return when (cakeOptionType) {
            in 1..3 -> "cake-option$cakeOptionType-add"
            else -> "404"
        }
    }

    @PostMapping("")
    fun addCakeOption(
        @RequestParam storeId: Long,
        @RequestParam cakeOptionType: Int,
        redirectAttributes: RedirectAttributes
    ): String {
        addStoreIdToModel(storeId, redirectAttributes)
        return "redirect:/operator/cake-option"
    }

    @DeleteMapping("{cakeOptionId}")
    fun deleteCakeOption(
        @RequestParam storeId: Long,
        @RequestParam cakeOptionType: Int,
        @PathVariable cakeOptionId: Long,
        redirectAttributes: RedirectAttributes
    ): String {
        addStoreIdToModel(storeId, redirectAttributes)
        return "redirect:/operator/cake-option"
    }

    private fun addStoreIdToModel(storeId: Long, model: Model) =
        model.addAttribute("storeId", storeId)
}