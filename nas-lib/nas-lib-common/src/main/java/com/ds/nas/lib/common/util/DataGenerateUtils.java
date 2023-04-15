package com.ds.nas.lib.common.util;

import lombok.Cleanup;

import java.io.*;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author ds
 * @date 2022/12/4
 * @description
 */
public final class DataGenerateUtils {

    private DataGenerateUtils() {
    }

    private static final int[] CITIES = {350602, 370782, 513431, 532624, 530426, 370203, 350128, 421002, 350624, 430225, 360300, 350203, 220211, 420822, 530625, 653126, 420203, 220182, 230603, 533323, 430121, 621225, 652827, 511500, 450205, 652824, 411402, 440781, 469022, 370214, 542521, 433101, 460100, 530381, 411722, 533400, 110229, 640300, 210700, 450127, 440105, 530828, 120000, 420000, 211402, 341823, 220402, 330500, 371324, 150500, 150927, 321284, 230231, 150926, 630123, 341700, 441400, 542330, 370684, 370828, 654326, 610831, 140300, 350581, 421182, 421200, 341124, 371423, 445302, 513225, 532522, 469026, 450102, 433130, 222406, 511325, 410328, 210422, 430405, 341100, 140212, 445222, 350403, 430521, 520111, 652924, 522201, 542327, 110103, 530826, 630122, 610527, 330481, 522424, 820000, 231081, 410103, 431223, 230524, 441284, 500226, 152524, 211382, 530300, 411102, 410727, 152223, 451200, 610203, 230805, 500116, 341222, 420324, 610500, 141128, 371202, 140426, 510181, 341021, 340421, 130623, 152529, 130626, 530902, 220102, 532801, 220183, 632122, 371622, 140721, 340121, 420503, 632524, 610327, 130500, 152923, 150422, 420528, 140221, 430502, 610921, 422822, 130627, 430105, 410926, 340603, 510321, 211202, 522729, 653100, 421123, 310104, 341282, 410602, 510304, 230712, 320481, 532627, 610800, 610522, 360829, 410105, 410106, 431126, 330127, 131126, 350428, 130930, 430621, 130724, 450681, 411381, 130208, 411200, 522327, 640105, 321282, 632323, 371481, 420800, 621124, 341825, 340300, 450923, 530500, 411423, 150302, 530821, 140802, 310115, 410203, 420116, 371724, 430922, 130800, 150502, 210711, 230207, 511529, 530325, 320402, 542300, 140723, 542221, 511800, 150430, 440700, 220421, 350181, 520329, 350784, 440115, 330304, 411221, 510107, 360803, 520221, 350603, 421003, 411522, 150205, 220503, 620421, 370303, 451031, 150525, 360700, 340711, 620403, 610924, 500117, 542626, 511922, 620800, 450123, 533123, 320900, 410423, 330902, 451002, 623027, 620923, 220204, 420923, 210922, 150429, 150929, 420684, 610427, 150624, 340702, 360313, 320300, 320600, 431228, 621000, 370323, 530421, 640104, 370503, 533421, 420102, 371428, 220105, 350981, 370113, 230503, 130925, 231025, 620300, 341524, 130127, 231200, 130224, 420606, 652922, 371426, 130402, 140826, 511304, 610724, 511823, 542100, 510303, 440113, 500114, 542337, 451381, 513336, 441427, 450311, 630105, 361122, 610829, 360425, 130128, 350105, 511525, 220202, 632724, 350205, 310112, 640303, 623023, 140726, 513229, 330103, 420204, 140725, 350500, 511826, 211322, 630102, 230822, 653022, 130426, 371121, 632200, 640521, 621100, 511524, 130803, 130982, 451202, 350600, 450324, 130424, 510800, 640324, 131028, 450305, 340828, 140423, 654028, 110114, 340503, 653128, 610823, 510184, 310105, 542622, 610821, 511602, 522730, 331000, 360100, 410421, 450107, 150826, 610525, 140922, 140107, 632522, 130283, 130121, 130431, 632523, 430223, 530112, 361023, 361181, 411421, 340302, 654325, 360733, 370322, 450126, 632721, 320924, 430321, 532625, 652927, 440523, 230302, 511702, 361025, 150725, 360731, 321183, 451024, 440404, 320205, 321302, 370306, 371322, 510311, 360102, 431124, 410883, 500238, 230110, 522422, 331123, 411403, 522229, 520424, 510682, 320000, 231102, 652101, 371000, 130107, 420527, 542121, 500112, 150425, 140225, 341623, 331100, 430522, 540123, 500000, 652923, 130726, 150223, 420529, 440783, 530921, 211003, 320281, 513223, 530700, 450400, 331004, 410303, 511681, 640205, 640424, 620821, 441424, 530522, 630000, 370634, 360726, 230604, 150523, 371302, 340323, 141023, 220581, 610431, 610524, 220724, 150922, 410600, 211103, 440800, 632222, 230505, 330211, 360721, 130406, 522627, 422826, 220122, 210882, 230826, 530102, 130921, 469002, 360424, 340123, 220502, 120112, 450422, 370830, 152201, 320107, 361028, 510104, 440923, 654000, 330621, 141034, 450803, 510727, 621222, 530000, 610825, 610302, 510400, 450109, 441202, 429021, 654201, 211300, 140624, 360302, 511528, 130108, 532527, 652826, 520303, 530825, 330322, 511600, 430281, 340406, 230624, 522223, 500235, 220281, 411503, 610322, 411330, 533100, 210122, 411726, 341102, 220181, 530626, 130826, 411622, 232722, 340825, 230102, 441500, 360826, 420111, 141082, 141182, 231281, 620200, 441581, 431129, 440183, 130604, 220800, 532329, 620321, 610526, 510000, 411224, 621022, 130428, 340521, 130205, 421087, 532621, 130628, 310118, 440902, 510502, 321000, 420583, 130804, 542522, 360103, 410800, 420113, 530829, 532524, 421125, 542301, 513424, 460107, 320830, 421000, 513230, 331024, 360222, 220303, 530602, 511522, 130823, 441802, 420881, 330200, 330727, 361124, 530113, 330803, 440114, 210211, 430726, 152526, 533321, 460000, 510600, 411282, 420281, 500118, 522625, 350426, 150522, 330382, 530424, 611023, 130532, 231084, 469031, 150300, 210600, 341302, 130185, 320400, 450330, 445102, 150404, 420506, 410311, 411623, 522401, 440803, 421022, 320114, 451226, 211421, 611024, 360200, 410700, 150222, 371203, 360123, 320703, 210282, 320322, 211221, 433100, 623025, 350505, 330108, 230803, 360732, 510124, 510302, 420106, 430211, 330183, 420205, 640323, 140224, 653226, 321102, 420984, 330424, 441882, 210800, 340500, 140932, 510703, 522631, 230127, 420582, 211302, 330503, 500242, 652302, 371626, 433123, 520321, 210900, 652900, 410184, 654221, 330881, 371081, 410104, 370829, 150104, 450521, 441223, 420600, 460106, 433125, 130630, 370211, 520114, 451423, 230381, 231085, 360321, 340223, 430412, 410811, 130303, 542127, 632621, 220103, 610830, 211121, 650106, 451324, 654322, 430224, 610626, 341821, 150981, 421023, 430203, 441827, 653201, 411000, 211081, 632127, 610624, 370321, 130638, 532530, 210204, 451321, 510525, 140924, 330326, 230203, 520328, 321081, 330782, 350583, 510623, 621025, 622926, 450222, 610404, 431322, 350123, 611021, 371400, 360900, 131023, 360111, 370902, 440281, 131081, 150421, 330329, 340104, 140200, 520200, 610822, 370700, 411600, 341721, 210811, 230705, 341502, 522623, 220622, 130184, 152200, 542424, 130200, 130730, 130400, 430721, 431026, 230902, 140729, 152502, 130522, 652925, 450303, 632626, 350504, 230100, 211011, 530428, 451030, 610422, 450405, 532327, 310108, 371327, 210100, 500113, 371102, 410221, 511821, 542122, 360122, 610828, 350721, 450502, 445321, 511825, 140108, 320504, 513432, 371325, 610623, 431202, 330825, 511725, 150621, 511129, 350623, 430682, 511133, 150103, 110116, 152202, 350481, 451425, 420504, 440300, 522425, 360823, 510403, 620103, 320382, 632722, 421024, 410326, 450421, 220882, 350724, 542329, 370900, 610304, 230500, 341800, 341000, 230882, 610622, 522626, 653124, 230700, 431123, 450122, 620402, 370523, 532628, 451227, 620422, 320721, 210681, 451302, 230707, 130525, 210727, 341421, 420922, 110109, 410402, 350925, 640400, 371122, 450100, 513430, 231224, 433122, 230208, 610727, 610303, 140226, 140429, 330102, 653225, 632726, 522230, 542125, 230703, 341723, 130207, 532502, 620724, 530321, 469028, 131128, 320115, 610402, 320723, 610328, 231002, 140702, 330683, 371312, 230421, 411121, 211303, 410922, 441624, 220700, 370405, 420300, 220282, 370784, 632625, 430529, 510524, 141123, 650200, 140430, 361123, 110000, 370283, 430124, 141181, 420105, 330122, 150100, 511521, 431122, 440308, 222424, 330903, 130625, 210102, 610423, 632624, 513200, 513231, 610521, 440229, 130727, 230230, 210202, 511621, 130404, 520422, 430681, 510521, 532532, 410204, 532323, 130126, 411625, 511100, 320623, 530822, 420381, 350423, 654202, 512022, 220702, 361030, 230711, 210111, 530128, 431302, 341321, 532328, 542334, 140121, 542523, 320507, 620902, 450221, 370612, 410724, 520122, 131182, 431002, 321300, 610502, 522222, 621121, 210403, 370284, 522300, 210423, 420302, 450602, 530326, 350902, 530722, 350781, 330109, 310230, 522723, 610326, 320111, 620822, 350525, 510823, 450981, 630100, 500240, 320305, 361128, 361024, 232700, 341023, 350782, 650202, 532926, 610103, 610923, 350200, 141028, 450512, 513332, 411721, 441481, 431381, 511000, 440205, 430703, 522325, 361129, 130132, 350924, 610632, 131121, 350211, 140421, 421100, 410803, 510626, 321181, 150428, 530624, 361027, 450902, 331082, 360921, 222403, 340881, 231182, 431025, 610926, 511400, 420625, 140322, 370786, 710000, 522328, 370304, 620121, 371523, 420683, 450702, 540124, 360428, 420505, 140105, 411323, 430921, 450921, 320206, 140223, 320506, 620623, 370212, 441200, 360983, 310120, 410400, 430423, 371723, 530422, 320724, 513325, 441324, 530600, 370400, 230722, 610728, 513335, 620982, 110107, 350429, 623021, 230708, 371328, 131082, 441825, 370783, 610400, 140781, 421122, 430300, 513429, 522634, 350825, 500115, 210504, 130722, 430923, 370686, 422827, 141030, 420802, 140603, 422828, 140923, 131026, 542200, 220623, 141027, 360203, 222402, 350824, 513331, 632801, 430603, 140823, 440513, 110102, 120225, 610429, 610826, 350430, 542231, 532531, 420100, 441881, 450200, 532525, 621002, 440606, 511822, 370681, 500101, 371326, 430100, 410900, 532822, 130824, 441226, 410482, 370831, 210321, 513427, 340404, 420104, 610621, 371700, 530622, 511421, 140500, 140829, 371103, 650100, 460108, 360600, 370881, 361021, 371082, 522732, 542226, 150800, 210323, 532301, 430722, 441625, 622901, 371425, 513333, 411321, 440811, 130721, 533325, 530111, 410782, 430624, 210702, 130130, 421222, 411327, 130624, 230108, 231222, 610582, 430981, 410224, 610700, 360830, 130705, 530802, 361125, 220106, 542427, 320411, 652926, 210903, 120223, 330682, 140600, 430700, 411602, 430702, 371726, 440282, 440982, 540122, 360827, 653121, 230715, 530323, 620721, 141031, 610112, 140902, 130183, 622923, 371625, 210603, 532325, 532500, 321100, 320113, 411023, 652122, 513434, 320321, 510105, 430304, 330104, 361127, 440608, 230123, 520112, 542129, 350122, 632128, 654300, 130635, 410381, 650105, 370205, 410181, 511181, 211321, 130733, 542128, 445323, 450821, 621221, 361000, 450329, 360622, 340207, 522428, 530827, 141130, 610631, 620111, 410000, 210522, 220104, 130631, 610102, 140825, 230710, 341702, 650103, 150727, 320802, 542126, 445122, 532528, 522722, 130433, 140921, 522725, 320684, 522200, 440605, 370685, 510903, 652825, 441521, 310103, 320103, 220221, 410711, 623026, 370124, 532600, 130421, 230111, 445100, 320505, 350104, 542422, 230621, 520421, 220323, 130302, 520302, 320405, 331021, 411624, 130203, 469027, 152523, 130133, 542338, 441323, 620500, 420981, 150221, 360782, 150802, 411082, 141100, 610323, 130923, 430611, 360423, 620722, 330206, 640381, 620700, 150921, 370406, 654002, 540127, 410222, 330411, 130629, 510802, 511025, 130322, 450000, 451402, 420321, 150124, 610723, 120110, 371100, 370000, 522601, 150823, 230206, 361026, 610000, 310114, 130223, 530924, 141021, 360802, 420902, 542623, 430725, 451300, 130125, 450332, 632700, 469033, 411329, 530721, 430406, 130637, 331023, 420114, 622921, 150822, 530630, 632725, 421221, 610104, 320705, 370481, 440400, 621126, 530328, 513437, 220723, 450105, 140728, 510812, 230605, 350722, 441623, 520121, 410325, 500236, 140727, 131181, 610116, 370403, 210000, 410411, 469023, 130100, 410122, 410523, 411681, 140122, 360730, 330324, 510421, 320700, 350527, 371500, 440402, 640302, 411024, 500107, 320124, 370921, 370682, 230225, 530900, 530124, 532523, 810000, 371422, 511527, 620823, 411081, 411724, 430822, 340403, 330802, 421083, 421281, 130928, 430811, 210802, 522624, 445281, 520102, 140927, 150625, 511523, 420325, 513330, 140623, 410324, 440825, 431221, 654003, 445300, 341004, 130521, 340208, 410802, 440000, 410183, 422823, 542222, 522426, 621200, 150928, 210803, 650203, 654301, 320903, 341422, 430202, 632121, 650204, 430524, 130481, 542430, 130981, 320324, 210400, 330381, 230183, 431128, 653130, 130929, 321088, 431127, 320921, 330824, 420322, 640181, 530126, 120111, 320804, 371321, 140621, 530628, 150304, 230300, 510114, 542331, 370826, 230704, 610426, 430511, 341622, 430602, 350821, 445202, 371323, 451229, 652829, 533423, 210283, 450600, 230321, 130523, 620100, 530400, 321322, 610824, 451027, 321011, 654223, 410725, 441300, 620522, 411729, 370882, 370785, 520113, 360430, 440233, 542524, 610329, 520325, 110112, 520203, 640122, 659001, 331126, 632822, 361126, 610222, 371524, 231004, 140181, 370305, 350926, 330282, 469006, 610114, 150303, 420624, 340321, 350400, 430421, 430781, 610204, 130534, 451400, 450500, 440785, 340405, 450300, 231000, 410200, 511111, 131003, 620922, 659004, 611000, 610100, 321111, 410825, 542333, 340811, 410225, 532324, 420804, 650205, 640100, 411324, 230804, 140425, 150627, 341802, 130530, 469007, 321023, 652300, 360426, 370213, 411525, 370704, 371424, 542124, 542133, 150200, 542527, 130634, 110104, 230184, 150423, 370102, 421121, 511028, 652328, 130123, 440604, 130535, 210726, 130432, 130706, 232723, 130229, 621228, 320323, 632622, 640121, 230304, 430424, 621224, 610702, 450223, 513224, 330783, 320800, 231202, 510723, 530621, 211422, 530129, 451026, 341221, 152531, 330600, 441900, 340803, 451102, 210213, 622922, 150700, 152221, 320482, 141033, 230900, 230124, 530103, 440222, 451025, 320582, 511902, 210604, 230422, 341822, 410108, 431027, 451225, 130723, 370200, 442000, 652123, 410526, 152530, 520402, 140424, 430381, 500225, 610902, 410302, 440983, 500232, 130225, 440512, 440232, 621026, 410182, 330900, 230221, 361022, 513401, 130300, 411727, 360429, 440802, 610929, 650121, 532927, 230523, 421102, 530925, 360727, 513300, 610722, 654023, 210505, 411122, 321324, 441423, 140123, 469000, 141122, 152900, 371525, 140830, 500233, 211102, 220881, 140722, 150821, 130527, 150207, 620981, 441203, 360728, 653224, 532331, 450503, 330212, 532823, 611002, 620702, 542332, 130429, 130430, 350723, 632600, 542624, 542526, 450226, 130632, 510725, 650107, 230903, 350402, 411100, 360403, 512081, 542132, 152921, 340400, 420500, 450721, 450722, 370112, 530823, 340602, 650104, 340700, 370125, 542232, 530423, 430103, 420700, 540121, 610481, 350305, 330602, 469029, 440882, 220822, 341225, 371721, 130822, 610802, 522726, 511722, 210300, 533124, 653123, 150924, 341003, 532322, 420525, 340800, 370285, 640200, 513433, 350923, 410702, 620621, 130529, 371600, 510724, 610625, 310116, 510122, 410306, 130984, 230904, 130321, 420117, 430426, 542225, 310109, 152522, 411104, 500231, 632802, 340122, 150902, 513328, 410425, 632221, 520330, 532526, 542425, 652100, 532932, 431300, 230702, 420682, 152222, 441426, 320502, 350503, 370687, 510106, 440600, 120115, 652800, 130304, 152525, 451222, 330182, 610126, 340304, 659003, 320200, 430382, 340504, 350425, 640422, 130000, 130423, 522633, 150923, 410928, 320811, 330681, 350900, 130204, 330784, 441602, 360322, 360502, 430581, 440305, 632300, 510704, 610428, 652928, 230128, 220721, 430723, 652929, 210411, 411723, 330110, 140106, 350783, 220722, 410422, 340823, 220300, 632800, 320105, 330522, 230223, 510683, 542123, 360781, 110106, 513322, 360923, 640106, 441823, 542400, 411502, 370602, 610122, 451023, 451481, 320923, 654200, 371502, 532922, 130202, 150526, 542336, 411425, 131002, 371623, 530502, 220582, 370703, 210112, 530922, 653122, 654021, 654022, 500109, 330303, 320585, 450403, 150723, 411728, 341125, 441700, 420581, 420626, 532623, 411500, 620924, 210224, 623022, 421224, 513221, 321323, 632123, 430821, 340621, 371522, 511102, 440306, 500111, 450881, 140900, 611025, 632525, 440511, 622924, 140400, 411103, 141081, 421181, 510811, 441303, 230306, 371402, 230303, 440224, 320826, 410923, 431081, 360724, 411002, 410102, 630104, 522622, 211282, 152528, 654024, 510108, 532326, 429006, 150125, 530181, 211481, 510182, 610528, 650109, 210781, 330300, 370404, 230716, 340822, 220100, 211005, 421126, 350100, 411481, 350524, 542627, 469021, 152224, 320621, 330502, 410527, 420112, 430481, 513323, 652200, 130636, 230407, 130582, 140321, 620600, 360800, 420921, 140100, 411422, 340802, 220284, 350921, 341024, 469003, 410726, 110117, 230307, 510921, 341522, 510132, 440903, 420526, 150900, 130633, 230281, 654025, 231024, 522224, 620400, 510411, 542335, 320211, 410500, 610730, 510300, 510681, 410223, 620104, 150400, 530402, 340000, 141125, 341402, 542421, 350700, 150782, 654321, 420222, 420821, 500241, 410505, 513329, 222400, 522632, 360725, 371200, 310117, 140800, 320503, 371728, 320381, 331122, 230600, 321182, 450302, 360926, 621021, 231005, 141002, 513428, 422801, 220521, 341503, 130903, 621024, 210113, 320202, 640202, 110113, 654027, 650000, 421124, 320304, 340102, 350303, 360602, 140929, 340311, 520327, 421202, 533122, 621102, 620000, 623001, 420107, 610726, 440106, 440100, 451000, 431028, 430400, 610430, 653125, 530723, 653200, 141121, 230200, 532925, 542233, 131100, 410581, 530322, 130926, 320682, 141102, 130181, 130621, 320611, 130983, 131025, 230781, 371482, 533324, 512000, 210503, 430221, 650201, 520181, 441781, 230400, 410404, 371526, 321202, 152527, 371521, 341181, 330921, 340604, 370923, 410704, 510522, 620824, 120105, 510900, 140622, 621023, 130227, 513334, 340200, 370302, 230125, 130182, 540125, 422825, 431230, 441302, 410822, 220681, 130324, 620826, 210181, 511781, 522728, 650102, 522600, 321112, 411281, 150602, 210203, 320829, 330726, 411725, 411523, 130703, 530425, 430122, 411527, 652722, 410728, 440804, 500106, 440900, 513233, 211404, 540000, 370725, 130425, 231121, 500103, 510781, 350111, 130683, 341400, 431022, 441702, 420103, 510504, 211122, 321084, 522727, 610324, 431003, 150206, 450922, 431102, 450224, 360922, 469005, 341521, 429004, 510700, 150783, 410211, 231100, 130524, 130528, 522225, 350213, 230713, 640221, 512002, 511402, 150724, 451224, 141022, 522226, 361100, 370521, 450423, 530324, 360824, 360500, 420702, 130732, 140928, 131022, 451228, 330402, 341203, 350625, 341002, 654225, 410403, 510821, 130827, 522629, 630103, 411325, 370832, 360734, 340111, 542600, 320584, 542328, 500102, 430407, 371727, 410506, 520323, 430302, 450481, 653127, 230714, 140222, 610725, 433126, 150600, 330100, 222404, 141026, 110101, 469030, 350103, 360681, 522322, 510129, 341200, 220322, 441225, 522630, 610827, 640423, 542223, 211004, 131127, 210281, 140428, 341722, 341103, 632821, 500243, 469001, 231123, 360481, 430500, 640522, 440184, 110111, 361002, 653131, 340703, 360729, 370983, 431121, 511123, 320100, 211223, 141025, 522423, 130434, 441422, 532928, 210402, 410781, 513400, 150825, 610331, 441821, 441502, 360825, 440500, 331003, 371624, 370126, 130922, 140211, 350702, 542228, 652901, 410304, 652723, 440104, 150121, 360521, 430104, 450321, 610424, 522702, 320412, 411303, 450124, 510322, 654226, 440103, 451323, 520123, 340303, 520322, 140881, 429005, 411700, 140581, 451021, 620523, 530926, 140981, 451028, 360202, 370300, 411025, 140602, 140524, 150781, 230706, 500237, 610900, 130581, 653101, 320925, 331102, 360881, 542429, 610928, 330203, 450700, 652700, 450802, 210602, 211381, 220602, 451424, 330483, 430528, 441402, 511622, 430111, 420602, 220400, 231221, 360281, 441800, 510402, 130700, 430800, 530627, 652327, 621227, 210421, 330723, 610425, 431224, 120103, 522636, 450804, 230202, 511126, 220422, 150626, 220802, 230305, 441224, 430408, 231083, 371427, 350822, 610628, 500200, 350212, 350000, 210304, 130825, 430626, 231003, 371311, 140110, 230227, 410927, 450621, 230229, 430802, 411626, 440307, 361029, 210804, 621122, 320203, 410603, 130900, 130681, 320583, 411426, 140822, 220605, 320982, 370100, 321003, 441322, 211281, 652223, 371621, 341600, 511300, 610630, 371083, 350124, 230204, 320102, 350521, 542525, 654224, 360735, 450103, 511425, 210911, 513426, 510100, 211403, 150424, 120114, 510121, 532800, 450326, 140522, 210624, 150721, 431100, 330800, 510115, 150123, 450703, 445224, 330105, 330328, 141029, 230406, 350725, 320922, 150403, 360925, 230623, 513326, 513338, 340824, 513232, 430600, 341423, 341424, 150521, 370282, 441283, 320282, 370827, 341324, 420900, 361102, 511424, 330000, 350626, 370281, 511721, 530121, 340203, 321200, 210124, 360121, 532622, 411322, 653024, 140521, 341202, 441600, 522326, 530125, 330226, 511422, 520425, 140427, 210404, 420303, 632521, 150581, 500108, 533300, 230205, 140824, 640402, 511302, 211224, 450924, 451123, 610922, 620524, 530824, 430482, 542426, 422800, 141126, 350622, 320404, 433124, 210104, 430527, 230521, 371300, 231124, 350182, 120221, 431321, 321203, 330283, 440112, 430204, 410202, 430724, 331181, 130927, 542621, 232721, 130924, 130129, 152500, 520100, 341323, 131000, 330523, 331125, 542625, 511024, 513327, 610925, 410300, 220600, 450203, 441721, 431200, 530523, 350982, 411702, 441622, 653000, 370781, 421300, 610581, 522700, 440304, 341122, 410503, 110228, 230800, 640502, 220524, 150785, 511323, 511423, 371581, 320116, 320681, 222405, 341525, 542428, 440116, 430503, 510922, 632100, 130725, 360981, 320104, 450108, 310106, 130728, 330624, 420323, 511923, 522635, 520326, 330400, 340502, 532300, 433127, 421381, 511303, 130702, 500222, 610927, 231223, 130403, 141024, 640000, 431225, 430422, 141129, 630121, 350628, 513436, 620122, 341824, 511124, 460105, 431281, 511322, 530927, 530427, 431024, 653223, 131124, 231283, 640425, 451223, 110108, 140000, 513222, 632321, 340827, 140926, 130435, 320125, 611026, 621123, 220621, 231225, 130427, 230828, 150524, 610111, 610403, 500234, 140525, 520103, 410882, 110115, 540100, 530800, 511526, 230104, 210682, 360821, 410185, 370982, 330482, 361121, 451221, 350424, 340402, 610202, 131122, 211100, 321281, 510503, 370522, 623024, 421081, 210782, 431103, 653023, 130821, 532930, 652823, 510722, 620725, 632224, 410323, 141127, 222426, 469032, 440981, 120113, 513226, 511011, 230709, 320902, 370500, 360124, 532900, 350322, 340202, 532501, 231282, 640500, 341204, 350526, 140930, 411400, 420607, 440784, 520381, 530629, 220500, 542325, 659000, 211400, 440203, 542322, 321002, 411627, 230502, 350800, 620302, 130828, 451121, 411628, 652325, 431226, 520400, 510112, 610721, 150925, 210521, 450603, 140109, 331002, 450800, 522400, 621223, 220000, 340322, 370105, 130729, 210114, 350206, 533422, 371722, 511381, 511724, 542423, 341621, 542229, 230403, 330204, 620825, 350302, 211002, 152501, 320303, 230109, 370800, 340721, 431000, 150702, 211324, 522323, 130682, 419001, 360000, 450331, 140724, 530524, 130684, 360828, 532626, 130533, 140481, 220200, 230833, 522731, 530127, 360402, 441826, 411521, 222401, 140827, 540126, 330521, 532929, 500119, 610602, 340221, 330185, 360421, 610729, 211021, 130531, 411202, 150000, 445121, 410327, 230382, 610330, 510904, 341523, 611022, 620105, 130731, 411528, 150426, 513337, 445221, 350427, 230000, 310101, 410305, 511132, 420703, 360723, 513324, 210502, 360105, 451122, 341226, 500223, 430525, 130230, 330822, 620503, 451281, 220203, 320803, 370502, 610627, 510603, 511502, 610124, 654026, 621226, 321283, 632623, 652222, 131125, 411621, 410322, 440507, 430623, 652301, 513435, 652201, 450900, 420502, 370181, 370705, 371329, 360104, 140931, 410100, 341602, 330702, 410502, 150105, 150623, 451322, 230129, 445200, 510500, 441523, 410821, 211000, 532931, 140821, 450328, 331022, 542323, 140227, 532901, 610113, 512021, 420704, 220821, 140431, 440883, 130526, 610115, 340103, 532923, 410329, 410522, 350823, 430102, 120104, 652801, 620423, 522228, 130600, 654323, 210881, 310000, 620521, 331124, 210200, 130131, 330703, 511112, 622925, 431382, 513321, 632324, 220382, 360400, 320706, 632322, 341022, 610600, 150722, 620622, 360924, 532529, 510726, 431227, 500110, 510113, 320831, 511900, 510923, 371421, 530923, 140828, 430902, 652828, 451421, 450225, 210381, 210905, 620123, 420200, 360427, 431222, 500229, 511321, 520324, 131123, 360902, 440204, 542224, 230405, 141124, 410611, 430903, 210921, 653001, 622900, 440607, 511827, 341322, 350681, 410721, 510824, 530521, 150622, 620900, 370683, 652323, 230606, 450325, 421127, 659002, 530623, 370811, 411424, 410823, 220302, 430523, 511802, 511921, 430200, 231226, 620802, 350582, 445381, 511700, 511623, 230103, 513422, 141000, 411526, 610523, 370613, 210904, 450322, 210703, 632723, 331127, 150726, 350629, 620525, 371725, 530100, 230602, 520382, 510822, 511824, 130281, 430900, 341500, 150784, 310110, 210123, 371602, 542227, 411300, 440515, 220381, 361130, 410902, 520201, 451422, 130603, 500105, 500230, 440703, 230182, 440303, 330421, 450304, 522324, 441723, 653221, 350802, 431229, 220112, 370600, 230921, 210500, 450327, 469025, 341300, 411302, 632823, 513227, 440823, 511723, 653129, 410621, 430000, 620602, 350922, 513423, 131102, 632126, 140925, 410703, 440705, 411328, 130622, 621125, 341182, 410804, 120106, 500104, 520423, 653222, 430181, 445322, 450204, 350502, 230224, 620921, 370202, 370883, 330700, 410205, 320981, 350300, 370724, 320311, 450404, 130124, 150203, 140700, 331121, 530724, 622927, 542324, 230126, 511113, 620502, 653227, 140303, 230622, 360323, 610125, 230881, 610629, 350627, 510183, 150122, 210103, 341881, 371702, 340222, 360702, 230522, 520222, 330225, 520000, 320581, 530122, 140882, 511324, 371002, 370611, 440514, 513425, 620723, 533103, 441621, 411326, 522227, 500224, 141032, 331081, 320722, 360982, 220283, 130323, 231181, 421223, 522628, 450125, 350421, 370911, 350125, 440403, 652324, 469024, 411222, 533102, 422802, 210212, 510422, 150402, 152922, 431023, 522427, 610300, 340826, 360722, 370104, 530302, 654324, 330281, 421303, 623000, 632500, 210311, 460200, 441421, 500227, 150824, 510131, 410481, 310113, 321311, 150202, 131024, 340600, 652701, 420982, 431125, 652822, 370702, 330302, 330327, 431021, 330922, 522301, 532924, 211200, 350881, 542326, 360822, 620102, 542500, 621202, 621027, 450323, 440200, 420202, 500228, 520300, 340100, 220523, 440704, 320500, 320204, 210105, 330781, 451022, 451029, 632223, 513228, 411524, 341126, 451100, 530702, 440881, 410622, 350121, 420115, 610200, 350304, 320602, 522701};

    private static final String[] SURNAME = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒", "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季"};

    private static final String GIRL = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";

    private static final String BOY = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";

    private static final String[] TEL_FIRST = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    private static final int[] CALC_C = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    private static final char[] CALC_R = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

    private static final String[] CARD_ID = {"C000401", "C001601", "C003301", "C001901", "C003501", "C002400"};

    private static final String[] ORG_ID = {"10099", "10098", "109000", "11111"};

    private static final String DELIMITER = ",";

    private static final String FILE_PATH = "/Users/doson/work/fileData/";

    private static final String FILE_NAME = "data.txt";

    public static int read(int pointe) {
        if (pointe == -1) {
            return 0;
        }
        RandomAccessFile raf = null;
        int hasRead = 0;
        try {
            raf = new RandomAccessFile(new File(FILE_PATH + FILE_NAME), "r");
            //获取RandomAccessFile对象文件指针的位置，初始位置是0
            System.out.println("RandomAccessFile文件指针的初始位置:" + raf.getFilePointer());
            //移动文件指针位置
            raf.seek(pointe);
            byte[] buff = new byte[1024];
            //用于保存实际读取的字节数
            //循环读取
            while ((hasRead = raf.read(buff)) > 0) {
                //打印读取的内容,并将字节转为字符串输入
                System.out.println(new String(buff, 0, hasRead));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return hasRead;
    }

    /**
     * 写入文件
     *
     * @param data
     * @throws Exception
     */
    public static void write(String data) throws Exception {
        File dir = new File(Paths.get(FILE_PATH).toUri());
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        String fileName = FILE_PATH + FILE_NAME;

        @Cleanup
        FileWriter fileWriter = new FileWriter(fileName);
        @Cleanup
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(data);
        writer.flush();
    }

    /**
     * 写入文件
     *
     * @param data
     * @throws Exception
     */
    public static void write(String data, String fileName) throws Exception {
        File file = new File(fileName);
        @Cleanup
        FileWriter fileWriter = new FileWriter(file);
        @Cleanup
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(data);
        writer.flush();
    }

    /**
     * 生成RwDataExcelBean
     *
     * @param count
     * @return
     */
    public static String generateStrRwData(int count) {
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < count; i++) {
            data.append(getOrgId())
                    .append(DELIMITER)
                    .append(generateName())
                    .append(DELIMITER)
                    .append("01")
                    .append(DELIMITER)
                    .append(getIdNo())
                    .append(DELIMITER)
                    .append(getCardId())
                    .append(DELIMITER)
                    .append(getCardNo())
                    .append("\r\n");
        }

        return data.toString();
    }

    /**
     * 生成AqDataExcelBean
     *
     * @param count
     * @return
     */
    public static String generateStrAqData(int count) {
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < count; i++) {
            data.append(generateName())
                    .append(DELIMITER)
                    .append("01")
                    .append(DELIMITER)
                    .append(getIdNo())
                    .append(DELIMITER)
                    .append(getTel())
                    .append(DELIMITER)
                    .append(getCardId())
                    .append(DELIMITER)
                    .append(getCardNo())
                    .append(DELIMITER)
                    .append(getCreditAmount())
                    .append("\r\n");
        }

        return data.toString();
    }

    public static List<String> generateListData(int count) {
        List<String> result = new ArrayList<>(count);
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < count; i++) {
            data.append(generateName())
                    .append(DELIMITER)
                    .append("01")
                    .append(DELIMITER)
                    .append(getIdNo())
                    .append(DELIMITER)
                    .append(getTel())
                    .append(DELIMITER)
                    .append(getCardId())
                    .append(DELIMITER)
                    .append(getCardNo())
                    .append(DELIMITER)
                    .append(getCreditAmount());
            result.add(data.toString());
            data.delete(0, data.length());
        }

        return result;
    }

    /**
     * 生成信用额度
     *
     * @return
     */
    public static int getCreditAmount() {
        return getNum(0, 10) * 10000;
    }

    /**
     * 随机生成orgId
     *
     * @return
     */
    public static String getOrgId() {
        Random random = new Random();
        int index = random.nextInt(ORG_ID.length);

        return ORG_ID[index];
    }

    /**
     * 随机生成卡产品编号
     *
     * @return
     */
    public static String getCardId() {
        Random random = new Random();
        int index = random.nextInt(CARD_ID.length);

        return CARD_ID[index];
    }

    /**
     * 生成卡号
     *
     * @return
     */
    public static String getCardNo() {
        String str1 = String.valueOf(10000000 + getNum(0, 9999999));
        String str2 = String.valueOf(10000000 + getNum(0, 9999999));

        return "622" + str1 + str2;
    }

    /**
     * 生成随机姓名
     *
     * @return name
     */
    public static String generateName() {
        Random random = new Random();
        int index = random.nextInt(SURNAME.length);
        //获得一个随机的姓氏
        String name = SURNAME[index];
        //可以根据这个数设置产生的男女比例
        int i = random.nextInt(3);
        if (i == 2) {
            int j = random.nextInt(GIRL.length() - 2);
            if (j % 2 == 0) {
                name = name + GIRL.substring(j, j + 2);
            } else {
                name = name + GIRL.substring(j, j + 1);
            }
        } else {
            int j = random.nextInt(BOY.length() - 2);
            if (j % 2 == 0) {
                name = name + BOY.substring(j, j + 2);
            } else {
                name = name + BOY.substring(j, j + 1);
            }
        }

        return name;
    }

    /**
     * 生成随机证件类型
     *
     * @return
     */
    public static String generateIdType() {
        return String.valueOf(getNum(0, 10));
    }

    /**
     * 返回手机号码
     */
    public static String getTel() {
        int index = getNum(0, TEL_FIRST.length - 1);
        String first = TEL_FIRST[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);

        return first + second + third;
    }

    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    public static String getIdNo() {
        //随机生成生日 1~99岁
        long begin = System.currentTimeMillis() - 3153600000000L;
        long end = System.currentTimeMillis() - 31536000000L;
        long rtn = begin + (long) (Math.random() * (end - begin));
        Date date = new Date(rtn);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String birth = simpleDateFormat.format(date);

        return getIdNo(birth, getNum(0, 1) % 2 == 1);
    }

    public static String getIdNo(String birth, boolean male) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int value = random.nextInt(CITIES.length);
        sb.append(CITIES[value]);
        sb.append(birth);
        value = random.nextInt(999) + 1;
        if (male && value % 2 == 0) {
            value++;
        }
        if (!male && value % 2 == 1) {
            value++;
        }
        if (value >= 100) {
            sb.append(value);
        } else if (value >= 10) {
            sb.append('0').append(value);
        } else {
            sb.append("00").append(value);
        }
        sb.append(calcTrailingNumber(sb));

        return sb.toString();
    }

    private static char calcTrailingNumber(StringBuilder sb) {
        int[] n = new int[17];
        int result = 0;
        for (int i = 0; i < n.length; i++) {
            n[i] = Integer.parseInt(String.valueOf(sb.charAt(i)));
        }
        for (int i = 0; i < n.length; i++) {
            result += CALC_C[i] * n[i];
        }

        return CALC_R[result % 11];
    }

}
