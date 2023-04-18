package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.adapter.`in`.web.dto.request.*
import com.cake.customcakebackend.adapter.`in`.web.dto.response.OperatorLoginResponse
import com.cake.customcakebackend.application.port.`in`.CakeOptionManagementUseCase
import com.cake.customcakebackend.common.CakeOption1Type
import com.cake.customcakebackend.common.CakeOption2Type
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping(
    path = ["/operator"]
)
class CakeOptionManagementController(
    private val cakeOptionManagementUseCase: CakeOptionManagementUseCase,
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
        httpServletRequest: HttpServletRequest,
        @SessionAttribute("operator") operatorLoginResponse: OperatorLoginResponse?,
        model: Model
    ): String {
        operatorLoginResponse
            ?: let {  // Session 에 Operator 정보가 없는 경우
                logger.info("케이크 옵션 리스트 정보 로드 실패: 운영자 정보 없음")
                return "redirect:/operator/login"
            }
        // TODO check (operatorId.storeId == storeID)
        operatorLoginResponse.storeId
            ?.let {
                val allCakeOptionMap = cakeOptionManagementUseCase.loadAllCakeOptionList(it)
                model.addAttribute("allCakeOptionMap", allCakeOptionMap)
            }

        return "cake-option-management"
    }

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
        httpServletRequest: HttpServletRequest,
        @SessionAttribute("operator") operatorLoginResponse: OperatorLoginResponse?,
        @RequestParam cakeOptionType: Int,
        model: Model
    ): String {
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
        httpServletRequest: HttpServletRequest,
        @SessionAttribute("operator") operatorLoginResponse: OperatorLoginResponse?,
        @ModelAttribute @Validated cakeOption1AddRequest: CakeOption1AddRequest,
        redirectAttributes: RedirectAttributes
    ): String {
        operatorLoginResponse
            ?: let {  // Session 에 Operator 정보가 없는 경우
                logger.info("케이크 옵션1 정보 로드 실패: 운영자 정보 없음")
                return "redirect:/operator/login"
            }

        operatorLoginResponse.storeId
            ?.let {
                cakeOptionManagementUseCase.saveCakeOption(it, 1, cakeOption1AddRequest)
            }

        return "redirect:/operator/cake-option"
    }

    @PostMapping("/cake-option-2")
    fun addCakeOption2(
        httpServletRequest: HttpServletRequest,
        @SessionAttribute("operator") operatorLoginResponse: OperatorLoginResponse?,
        @ModelAttribute @Validated cakeOption2AddRequest: CakeOption2AddRequest,
        redirectAttributes: RedirectAttributes
    ): String {
        operatorLoginResponse
            ?: let {  // Session 에 Operator 정보가 없는 경우
                logger.info("케이크 옵션1 정보 로드 실패: 운영자 정보 없음")
                return "redirect:/operator/login"
            }

        operatorLoginResponse.storeId
            ?.let {
                cakeOptionManagementUseCase.saveCakeOption(it, 1, cakeOption2AddRequest)
            }

        return "redirect:/operator/cake-option"
    }

    @PostMapping("/cake-option-3")
    fun addCakeOption3(
        httpServletRequest: HttpServletRequest,
        @SessionAttribute("operator") operatorLoginResponse: OperatorLoginResponse?,
        @ModelAttribute @Validated  cakeOption3AddRequest: CakeOption3AddRequest,
        redirectAttributes: RedirectAttributes
    ): String {
        operatorLoginResponse
            ?: let {  // Session 에 Operator 정보가 없는 경우
                logger.info("케이크 옵션1 정보 로드 실패: 운영자 정보 없음")
                return "redirect:/operator/login"
            }

        operatorLoginResponse.storeId
            ?.let {
                cakeOptionManagementUseCase.saveCakeOption(it, 1, cakeOption3AddRequest)
            }

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
        httpServletRequest: HttpServletRequest,
        @SessionAttribute("operator") operatorLoginResponse: OperatorLoginResponse?,
        @RequestParam cakeOptionType: Int,
        @PathVariable cakeOptionId: Long,
        redirectAttributes: RedirectAttributes
    ): String {
        operatorLoginResponse
            ?: let {  // Session 에 Operator 정보가 없는 경우
                logger.info("케이크 옵션1 삭제 실패: 운영자 정보 없음")
                return "redirect:/operator/login"
            }
        cakeOptionManagementUseCase.deleteCakeOption(cakeOptionType, cakeOptionId)

        return "redirect:/operator/cake-option"
    }
}