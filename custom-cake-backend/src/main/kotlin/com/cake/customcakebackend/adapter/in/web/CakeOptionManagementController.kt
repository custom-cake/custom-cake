package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.adapter.`in`.web.dto.request.CakeOption1AddRequest
import com.cake.customcakebackend.adapter.`in`.web.dto.request.CakeOption2AddRequest
import com.cake.customcakebackend.adapter.`in`.web.dto.request.CakeOption3AddRequest
import com.cake.customcakebackend.application.port.`in`.CakeOptionManagementUseCase
import com.cake.customcakebackend.application.port.`in`.StoreManagementUseCase
import com.cake.customcakebackend.common.CakeOption1Type
import com.cake.customcakebackend.common.CakeOption2Type
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.validation.Valid

@Controller
@RequestMapping(
    path = ["/operator/cake-option"]
)
class CakeOptionManagementController(
    private val cakeOptionManagementUseCase: CakeOptionManagementUseCase,
    private val storeManagementUseCase: StoreManagementUseCase
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
        @RequestParam operatorId: Long,
        @RequestParam storeId: Long,
        model: Model
    ): String {
        // TODO check (operatorId.storeId == storeID)

        val allCakeOptionMap = cakeOptionManagementUseCase.loadAllCakeOptionList(storeId)
        // 매장 check
        model.addAttribute("hasStore", storeManagementUseCase.hasStore(operatorId))

        addAttributeToModel("operatorId", operatorId, model)
        addAttributeToModel("storeId", storeId, model)
        model.addAttribute("allCakeOptionMap", allCakeOptionMap)
        return "cake-option-management"
    }

    @GetMapping("{cakeOptionId}")
    fun cakeOptionInfo(
        @RequestParam operatorId: Long,
        @RequestParam storeId: Long,
        @RequestParam cakeOptionType: Int,
        @PathVariable cakeOptionId: Long,
        model: Model
    ): String {
        addAttributeToModel("operatorId", operatorId, model)
        addAttributeToModel("storeId", storeId, model)
        return "cake-option-detail"
    }

    @GetMapping("/form")
    fun addCakeOptionForm(
        @RequestParam operatorId: Long,
        @RequestParam storeId: Long,
        @RequestParam cakeOptionType: Int,
        model: Model
    ): String {
        // 매장 check
        model.addAttribute("hasStore", storeManagementUseCase.hasStore(operatorId))

        addAttributeToModel("operatorId", operatorId, model)
        addAttributeToModel("storeId", storeId, model)
         when (cakeOptionType) {
//            in 1..3 -> "cake-option$cakeOptionType-add"
            1 -> {
                model.addAttribute("cakeOption1AddRequest", CakeOption1AddRequest())
                model.addAttribute("cakeShapeList", CakeOption1Type.CakeShape.toList())
                model.addAttribute("cakeSizeList", CakeOption1Type.CakeSize.toList())
                model.addAttribute("cakeLayerList", CakeOption1Type.CakeLayer.toList())
                return "cake-option1-add"
            }
            2 -> {
                model.addAttribute("cakeOption2AddRequest", CakeOption2AddRequest())
                model.addAttribute("cakeSheetList", CakeOption2Type.CakeSheet.toList())
                model.addAttribute("cakeInnerCreamList", CakeOption2Type.CakeInnerCream.toList())
                model.addAttribute("cakeOuterCreamList", CakeOption2Type.CakeOuterCream.toList())
                return "cake-option2-add"
            }
            3 -> {
                model.addAttribute("cakeOption3AddRequest", CakeOption3AddRequest())
                return "cake-option3-add"
            }
            else -> return "404"
        }
    }

    @PostMapping("")
    fun addCakeOption(
        @RequestParam operatorId: Long,
        @RequestParam storeId: Long,
        @RequestParam cakeOptionType: Int,
        @Valid @ModelAttribute cakeOption1AddRequest: CakeOption1AddRequest,
        redirectAttributes: RedirectAttributes
    ): String {
        addAttributeToModel("operatorId", operatorId, redirectAttributes)
        addAttributeToModel("storeId", storeId, redirectAttributes)
        return "redirect:/operator/cake-option"
    }

    @DeleteMapping("{cakeOptionId}")
    fun deleteCakeOption(
        @RequestParam operatorId: Long,
        @RequestParam storeId: Long,
        @RequestParam cakeOptionType: Int,
        @PathVariable cakeOptionId: Long,
        redirectAttributes: RedirectAttributes
    ): String {
        addAttributeToModel("operatorId", operatorId, redirectAttributes)
        addAttributeToModel("storeId", storeId, redirectAttributes)
        return "redirect:/operator/cake-option"
    }

    private fun addAttributeToModel(attributeName: String, id: Long, model: Model) =
        model.addAttribute(attributeName, id)
}