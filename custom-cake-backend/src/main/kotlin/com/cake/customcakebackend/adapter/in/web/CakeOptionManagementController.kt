package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.adapter.`in`.web.dto.request.*
import com.cake.customcakebackend.application.port.`in`.CakeOptionManagementUseCase
import com.cake.customcakebackend.application.port.`in`.StoreManagementUseCase
import com.cake.customcakebackend.common.CakeOption1Type
import com.cake.customcakebackend.common.CakeOption2Type
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.validation.Valid

@Controller
@RequestMapping(
    path = ["/operator"]
)
class CakeOptionManagementController(
    private val cakeOptionManagementUseCase: CakeOptionManagementUseCase,
    private val storeManagementUseCase: StoreManagementUseCase
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    /**
     * cakeOptionList method
     * : 해당 매장의 모든 option(1~3) 리스트
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/04/09
    **/
    @GetMapping("/cake-option")
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

//    @GetMapping("{cakeOptionId}")
//    fun cakeOptionInfo(
//        @RequestParam operatorId: Long,
//        @RequestParam storeId: Long,
//        @RequestParam cakeOptionType: Int,
//        @PathVariable cakeOptionId: Long,
//        model: Model
//    ): String {
//        addAttributeToModel("operatorId", operatorId, model)
//        addAttributeToModel("storeId", storeId, model)
//        return "cake-option-detail"
//    }

    /**
     * addCakeOptionForm method
     * : 케이크 옵션 form
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/04/09
    **/
    @GetMapping("/cake-option/form")
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
            1 -> {
                model.addAttribute("cakeOption1AddRequest", CakeOption1AddRequest())
                model.addAttribute("cakeShapeMap", CakeOption1Type.CakeShape.toMap())
                model.addAttribute("cakeSizeMap", CakeOption1Type.CakeSize.toMap())
                model.addAttribute("cakeLayerMap", CakeOption1Type.CakeLayer.toMap())
                return "cake-option1-add"
            }
            2 -> {
                model.addAttribute("cakeOption2AddRequest", CakeOption2AddRequest())
                model.addAttribute("cakeSheetMap", CakeOption2Type.CakeSheet.toMap())
                model.addAttribute("cakeInnerCreamMap", CakeOption2Type.CakeInnerCream.toMap())
                model.addAttribute("cakeOuterCreamMap", CakeOption2Type.CakeOuterCream.toMap())
                return "cake-option2-add"
            }
            3 -> {
                model.addAttribute("cakeOption3AddRequest", CakeOption3AddRequest())
                return "cake-option3-add"
            }
            else -> return "404"
        }
    }

    /**
     * addCakeOption method
     * : 케이크 옵션 등록
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/04/09
    **/
    @PostMapping("/cake-option-1")
    fun addCakeOption1(
        @RequestParam operatorId: Long,
        @RequestParam storeId: Long,
        @ModelAttribute @Valid  cakeOption1AddRequest: CakeOption1AddRequest,
        redirectAttributes: RedirectAttributes
    ): String {
        val (type, optionId) = cakeOptionManagementUseCase.saveCakeOption(storeId, 1, cakeOption1AddRequest)

        addAttributeToModel("operatorId", operatorId, redirectAttributes)
        addAttributeToModel("storeId", storeId, redirectAttributes)
        return "redirect:/operator/cake-option"
    }

    @PostMapping("/cake-option-2")
    fun addCakeOption2(
        @RequestParam operatorId: Long,
        @RequestParam storeId: Long,
        @ModelAttribute @Valid cakeOption2AddRequest: CakeOption2AddRequest,
        redirectAttributes: RedirectAttributes
    ): String {
        val (type, optionId) = cakeOptionManagementUseCase.saveCakeOption(storeId, 2, cakeOption2AddRequest)

        addAttributeToModel("operatorId", operatorId, redirectAttributes)
        addAttributeToModel("storeId", storeId, redirectAttributes)
        return "redirect:/operator/cake-option"
    }

    @PostMapping("/cake-option-3")
    fun addCakeOption3(
        @RequestParam operatorId: Long,
        @RequestParam storeId: Long,
        @ModelAttribute @Valid  cakeOption3AddRequest: CakeOption3AddRequest,
        redirectAttributes: RedirectAttributes
    ): String {
        val (type, optionId) = cakeOptionManagementUseCase.saveCakeOption(storeId, 3, cakeOption3AddRequest)
        addAttributeToModel("operatorId", operatorId, redirectAttributes)
        addAttributeToModel("storeId", storeId, redirectAttributes)
        return "redirect:/operator/cake-option"
    }


    /**
     * deleteCakeOption method
     * : 케이크 옵션 삭제
     *
     * @author jjaen
     * @version 1.0.0
     * 작성일 2023/04/09
    **/
    @DeleteMapping("/cake-option/{cakeOptionId}")
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