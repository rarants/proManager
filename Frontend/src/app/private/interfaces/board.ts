import { Column } from "./column";

export interface Board {
    id: Number,
    title: String,
    description: String,
    columns: Column[]
}
