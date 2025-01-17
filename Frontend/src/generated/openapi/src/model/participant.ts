/**
 * Participant Management Module
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.2
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
import { Type } from './type';


export interface Participant { 
    code: string;
    bic: string;
    name: string;
    shortName: string;
    type: Type;
    logo: string;
    settlementBank?: string;
}
export namespace Participant {
}


