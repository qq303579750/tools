package com.mingyuan.wxy.tools;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

public enum ProjectEnum {

    ANNOUNCE("内网"),
    STANDARD("标准"),
    CTMS("合同");
    private final String cnName;

    ProjectEnum(String cnName) {
        this.cnName = cnName;
    }

    public static ProjectEnum get(String code) {
        if (isEmpty(code)) {
            return null;
        }
        for (ProjectEnum projectEnum : ProjectEnum.values()) {
            if (code.equals(projectEnum.name())) {
                return projectEnum;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String[] aa = {
                "dimiscerti2025050817030358415911",
                "dimiscerti2025050817013186332206",
                "dimiscerti2025050717023268528604",
                "dimiscerti2025050717013187128228",
                "dimiscerti2025050717010113992727",
                "dimiscerti2025050617005131612119",
                "dimiscerti2025050517020227131880",
                "dimiscerti2025050517010144282321",
                "dimiscerti2025050517002082476597",
                "dimiscerti2025050517001047633306",
                "dimiscerti2025050417003042244174",
                "dimiscerti2025050317005086820255",
                "dimiscerti2025050317001023867108",
                "dimiscerti2025050217003105201603",
                "dimiscerti2025050217001042805888",
                "dimiscerti2025050117051913000986",
                "dimiscerti2025050117050879849263",
                "dimiscerti2025050117031618609175",
                "dimiscerti2025050117012275241715",
                "dimiscerti2025050117011240504789",
                "dimiscerti2025042517000007502731",
                "dimiscerti2025042417005200119647",
                "dimiscerti2025042317000021911106",
                "dimiscerti2025042217005117337931",
                "dimiscerti2025042117010087574192",
                "dimiscerti2025042017010061349292",
                "dimiscerti2025041817022341174659",
                "dimiscerti2025041817002062151828",
                "dimiscerti2025041717005123297665",
                "dimiscerti2025041617011198495048",
                "dimiscerti2025041617010170236015",
                "dimiscerti2025041617005134873968",
                "dimiscerti2025041617002083257638",
                "dimiscerti2025041517001041579494",
                "dimiscerti2025041417012134217614",
                "dimiscerti2025041417001043188728",
                "dimiscerti2025041217002076566676",
                "dimiscerti2025041117003119860383",
                "dimiscerti2025041017020235975428",
                "dimiscerti2025041017014192917184",
                "dimiscerti2025041017010135269682",
                "dimiscerti2025040817030190881090",
                "dimiscerti2025040817025180597522",
                "dimiscerti2025040817013102829847",
                "dimiscerti2025040817010073633547",
                "dimiscerti2025040817005063698875",
                "dimiscerti2025040717014103438793",
                "dimiscerti2025040517004047432442",
                "dimiscerti2025040417020124765657",
                "dimiscerti2025040417015114464961",
                "dimiscerti2025040417014104524410",
                "dimiscerti2025040317011076171148",
                "dimiscerti2025040217044341154325",
                "dimiscerti2025040217040301796652",
                "dimiscerti2025040217035262856404",
                "dimiscerti2025040117044284227932",
                "dimiscerti2025040117040247517665",
                "dimiscerti2025040117032206213512",
                "dimiscerti2025040117025176565510",
                "dimiscerti2025040117024167109554",
                "dimiscerti2025040117011077221073",
                "dimiscerti2025033117012088323875",
                "dimiscerti2025032717005072798785",
                "dimiscerti2025032617013135071285",
                "dimiscerti2025032617005091780836",
                "dimiscerti2025032117010079583915",
                "dimiscerti2025032017010092558288",
                "dimiscerti2025031617000011980981",
                "dimiscerti2025031317002039973945",
                "dimiscerti2025031317000008374031",
                "dimiscerti2025031217010092194077",
                "dimiscerti2025031017010085905183",
                "dimiscerti2025031017003044740886",
                "dimiscerti2025031017001022506384",
                "dimiscerti2025030817000007739735",
                "dimiscerti2025030717013120950680",
                "dimiscerti2025030717001022516368",
                "dimiscerti2025030617022181219965",
                "dimiscerti2025030617015145759686",
                "dimiscerti2025030617014132281906",
                "dimiscerti2025030617012108192611",
                "dimiscerti2025030617001020220642",
                "dimiscerti2025030517011095565122",
                "dimiscerti2025030417024258046137",
                "dimiscerti2025030417020208819614",
                "dimiscerti2025030317022176240526",
                "dimiscerti2025030217000011580081",
                "dimiscerti2025030117040282328986",
                "dimiscerti2025030117005077874078",
                "dimiscerti2025022717004059294436",
                "dimiscerti2025022317001022982951",
                "dimiscerti2025022217003059072116",
                "dimiscerti2025021717001017901371",
                "dimiscerti2025021717000008229899",
                "dimiscerti2025021417000021116020",
                "dimiscerti2025021317001049627861",
                "dimiscerti2025021217000009218490",
                "dimiscerti2025020817002036849780",
                "dimiscerti2025020717000008031169",
                "dimiscerti2025020517002040895645",
                "dimiscerti2025020117001043330484",
                "dimiscerti2025012817000010091448",
                "dimiscerti2025012117101897193371",
                "dimiscerti2025012117095875807934",
                "dimiscerti2025012117094864489930",
                "dimiscerti2025012117090822230591",
                "dimiscerti2025012117085811336198",
                "dimiscerti2025012117081769909629",
                "dimiscerti2025012117065677559905",
                "dimiscerti2025012117061631040292",
                "dimiscerti2025012117053587306860",
                "dimiscerti2025012117051563522979",
                "dimiscerti2025012117050551705710",
                "dimiscerti2025012117022382609958",
                "dimiscerti2025012117021372294680",
                "dimiscerti2025012117020361549537",
                "dimiscerti2025012117015349567191",
                "dimiscerti2025012117014338881797",
                "dimiscerti2025012117013328083577",
                "dimiscerti2025012117010110652788",
                "dimiscerti2025012117005099030400",
                "dimiscerti2025012117004088256980"
        };

        
    }
}
