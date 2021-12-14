import { Collumn } from "./collumn";

export interface Board {
    id: Number,
    title: String,
    description: String,
    collumns: Collumn[]
}
